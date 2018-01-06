package org.markjay.services;

import org.markjay.model.Company;
import org.markjay.model.IndexedCompany;
import org.markjay.model.InputDataRow;
import org.markjay.model.OutputDataRow;
import org.markjay.services.logging.Color;
import org.markjay.services.logging.ColoredString;
import org.markjay.services.logging.LoggerService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.markjay.services.logging.ColoredString.*;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 1/6/18 7:15 PM
 */
public class MapperService {
    
    SimilarityService similarityService = new LevenshteinStringSimilarity();
    LoggerService loggerService = new LoggerService();
    
    public List<OutputDataRow> run(ArrayList<Company> companies, ArrayList<InputDataRow> input) {
        return input.stream()
                .map(inputRow -> mapRow(companies, inputRow))
                .collect(Collectors.toList());
    }

    private OutputDataRow mapRow(ArrayList<Company> companies, InputDataRow input) {
        return new OutputDataRow(
                input.getRecordId(),
                input.getHuinya(),
                input.getCompany(),
                resolveCompany(companies, input.getCompany())
        );
    }

    private String resolveCompany(ArrayList<Company> companies, String company) {
        IndexedCompany indexedCompany = companies.parallelStream()
                .map(comp -> new IndexedCompany(comp, similarityService.calcSimilarity(comp.getCompanyName(), company)))
                .max(Comparator.comparing(IndexedCompany::getIndex))
                .get();

        loggerService.print(
                noColor("Company "), colored(Color.ANSI_RED, company),
                noColor(" was resolved to "), colored(Color.ANSI_BLUE, indexedCompany.getCompany().toString()),
                noColor(" with index = "), colored(Color.ANSI_GREEN, indexedCompany.getIndex()+"")
        );
        return indexedCompany.getCompany().getCompanyCode();
    }
}
