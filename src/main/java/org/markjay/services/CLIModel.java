package org.markjay.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 1/6/18 7:13 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CLIModel {
    @CommandLine.Parameters(hidden = true)  // "hidden": don't show this parameter in usage help message
            List<String> allParameters; // no "index" attribute: captures _all_ arguments (as Strings)

    @CommandLine.Parameters(index = "0")
    File inputFile1;
    @CommandLine.Parameters(index = "1")
    File inputFile2;
    @CommandLine.Parameters(index = "2")
    File outputFile;

    public void populate(String[] args) {
        CommandLine.populateCommand(this, args);
    }
}
