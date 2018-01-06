package org.markjay;

import org.markjay.model.Company;
import org.markjay.model.InputDataRow;
import org.markjay.model.OutputDataRow;
import org.markjay.services.CLIModel;
import org.markjay.services.CSVSerializer;
import org.markjay.services.MapperService;

import java.util.ArrayList;
import java.util.List;

public class App {

    private final static CSVSerializer serializer = new CSVSerializer();
    private final static MapperService mapper = new MapperService();

    public static void main(String[] args) throws Exception {
        CLIModel cliModel = new CLIModel();
        cliModel.populate(args);

        System.out.println("cliModel = " + cliModel);

        ArrayList<Company> companies = serializer.parse(Company.class, cliModel.getInputFile1());
        ArrayList<InputDataRow> input = serializer.parse(InputDataRow.class, cliModel.getInputFile2());

        List<OutputDataRow> res = mapper.run(companies, input);
        serializer.printToFile(res, cliModel.getOutputFile());
    }
}
