package org.markjay.services;

import org.junit.Assert;
import org.junit.Test;
import org.markjay.model.Company;
import org.markjay.model.InputDataRow;
import org.markjay.model.OutputDataRow;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MapperServiceIT {

    @Test
    public void testRun() throws Exception {
        CSVParser parser = new CSVParser();
        MapperService mapper = new MapperService();
        ArrayList<Company> companies = parser.parse(Company.class, "Input_1_Company_list.txt");
        ArrayList<InputDataRow> input = parser.parse(InputDataRow.class, "Input_2_Main_file.txt");
        List<OutputDataRow> output = parser.parse(OutputDataRow.class, "Output_Main_file.txt");

        Assert.assertEquals(8, companies.size());
        Assert.assertEquals(8, input.size());

        List<OutputDataRow> res = mapper.run(companies, input);
        Assert.assertEquals(output, res);
    }
}