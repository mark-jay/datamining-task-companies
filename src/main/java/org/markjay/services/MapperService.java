package org.markjay.services;

import org.markjay.model.Company;
import org.markjay.model.IndexedCompany;
import org.markjay.model.InputDataRow;
import org.markjay.model.OutputDataRow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 1/6/18 7:15 PM
 */
public class MapperService {
    
    SimilarityService similarityService = new LevenshteinStringSimilarity();
    
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
        IndexedCompany indexedCompany = companies.stream()
                .map(comp -> new IndexedCompany(comp, similarityService.calcSimilarity(comp.getCompanyName(), company)))
                .max(Comparator.comparing(IndexedCompany::getIndex))
                .get();
        System.out.println("Company " + company + " was resolved to " + indexedCompany);
        return indexedCompany.getCompany().getCompanyCode();
    }
}
