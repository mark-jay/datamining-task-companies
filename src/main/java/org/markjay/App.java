package org.markjay;

import picocli.CommandLine;

import java.io.File;
import java.net.InetAddress;
import java.util.List;

import static picocli.CommandLine.*;

public class App {
    @Parameters(hidden = true)  // "hidden": don't show this parameter in usage help message
            List<String> allParameters; // no "index" attribute: captures _all_ arguments (as Strings)

    @Parameters(index = "0") File inputFile1;
    @Parameters(index = "1") File inputFile2;

    public static void main(String[] args) {
        App app = new App();
        CommandLine.populateCommand(app, args);
        System.out.println("app.inputFile1 = " + app.inputFile1);
        System.out.println("app.inputFile2 = " + app.inputFile2);
    }
}
