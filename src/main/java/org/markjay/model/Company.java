package org.markjay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 1/6/18 6:50 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@CsvDataType()
public class Company {
    @CsvField(pos = 1)
    private String companyCode;
    @CsvField(pos = 2)
    private String companyName;
}
