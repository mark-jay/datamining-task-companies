package org.markjay.services;

import picocli.CommandLine;

import java.io.File;
import java.util.List;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 1/6/18 7:13 PM
 */
public class CLIParser {
    @CommandLine.Parameters(hidden = true)  // "hidden": don't show this parameter in usage help message
            List<String> allParameters; // no "index" attribute: captures _all_ arguments (as Strings)

    @CommandLine.Parameters(index = "0")
    File inputFile1;
    @CommandLine.Parameters(index = "1") File inputFile2;

    public void run(String[] args) {
        CLIParser app = new CLIParser();
        CommandLine.populateCommand(app, args);
        System.out.println("app.inputFile1 = " + app.inputFile1);
        System.out.println("app.inputFile2 = " + app.inputFile2);

    }
}
