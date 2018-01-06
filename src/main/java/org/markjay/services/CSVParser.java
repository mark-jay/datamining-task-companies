package org.markjay.services;

import net.sf.jsefa.csv.CsvDeserializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 1/6/18 7:00 PM
 */
public class CSVParser {
    public <T> ArrayList<T> parse(Class<T> clazz, String filename) throws IOException {
        CsvConfiguration config = new CsvConfiguration();
        config.setFieldDelimiter(',');

        CsvIOFactory factory = CsvIOFactory.createFactory(config, clazz);
        CsvDeserializer deserializer = factory.createDeserializer();
        ArrayList<T> res = new ArrayList<T>();
        try (InputStream inputStream = CSVParser.class.getClassLoader().getResourceAsStream(filename)) {
            deserializer.open(new InputStreamReader(inputStream));
            deserializer.next(); // drop first

            while (deserializer.hasNext()) {
                T t = deserializer.next();
                res.add(t);
            }
            deserializer.close(true);
        }
        return res;
    }
}
