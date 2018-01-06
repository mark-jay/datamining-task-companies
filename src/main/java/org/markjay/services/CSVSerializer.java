package org.markjay.services;

import net.sf.jsefa.csv.CsvDeserializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.CsvSerializer;
import net.sf.jsefa.csv.config.CsvConfiguration;
import net.sf.jsefa.csv.lowlevel.config.QuoteMode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 1/6/18 7:00 PM
 */
public class CSVSerializer {
    public <T> ArrayList<T> parse(Class<T> clazz, String filename) throws IOException {
        try (InputStream inputStream = CSVSerializer.class.getClassLoader().getResourceAsStream(filename)) {
            return parse(clazz, inputStream);
        }
    }

    public <T> ArrayList<T> parse(Class<T> clazz, File file) throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {
            return parse(clazz, inputStream);
        }
    }

    public <T> void printToFile(List<T> list, File file) throws IOException {
        CsvSerializer serializer = getFactoryWriter(list.get(0).getClass()).createSerializer();
        serializer.open(new FileWriter(file));
        try {
            for (T entry : list) {
                serializer.write(entry);
            }
        } finally {
            serializer.close(true);
        }
    }

    private <T> ArrayList<T> parse(Class<T> clazz, InputStream inputStream) {
        CsvDeserializer deserializer = getFactoryReader(clazz).createDeserializer();
        ArrayList<T> res = new ArrayList<T>();

        deserializer.open(new InputStreamReader(inputStream));
        try {
            deserializer.next(); // drop first

            while (deserializer.hasNext()) {
                T t = deserializer.next();
                res.add(t);
            }

            return res;
        } finally {
            deserializer.close(true);
        }
    }

    private <T> CsvIOFactory getFactoryReader(Class<T> clazz) {
        CsvConfiguration config = new CsvConfiguration();
        config.setFieldDelimiter(',');

        return CsvIOFactory.createFactory(config, clazz);
    }

    private <T> CsvIOFactory getFactoryWriter(Class<T> clazz) {
        CsvConfiguration config = new CsvConfiguration();
        config.setFieldDelimiter(',');
        config.setQuoteCharacter('"');
        config.setDefaultQuoteMode(QuoteMode.ALWAYS);

        return CsvIOFactory.createFactory(config, clazz);
    }
}
