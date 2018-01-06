package org.markjay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 1/6/18 7:56 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class IndexedCompany {
    private Company company;
    private double index;
}
