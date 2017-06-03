package main.java.Uniq;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;

public class UniqLauncher {
    @Option(name = "-i", metaVar = "insensetiveCase", usage = "Insensitive case")
    private boolean insensetiveCase = false;

    @Option(name = "-s", metaVar = "IgnoreNSymbols", usage = "N symbols ignoring")
    private int ignoreNSymbols = 0;

    @Option(name = "-u", metaVar = "uniqueStrings", usage = "Unique strings")
    private boolean uniqueStrings = false;

    @Option(name = "-c", metaVar = "numberOfEqualStrings", usage = "Number of equal strings")
    private boolean numberOfEqualStrings = false;

    @Option(name = "-o", metaVar = "ofile", usage = "Output file name")
    private String outputFileName = null;

    @Argument(metaVar = "file", usage = "Input file name")
    private String inputFileName = null;

    public static void main(String[] args) {
        new UniqLauncher().launch(args);
    }

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Uniq.jar [-i] [-u] [-c] [-s num] [-o ofile] [file]");
            parser.printUsage(System.err);
            return;
        }

       Uniq uniq = new Uniq(insensetiveCase, ignoreNSymbols, uniqueStrings, numberOfEqualStrings);
        try {
            Reader input;
            if (inputFileName != null) {
                input = new FileReader(inputFileName);
            } else {
                input = new BufferedReader(new InputStreamReader(System.in));
            }

            Writer output;
            if (outputFileName != null) {
                try {
                    output = new FileWriter(outputFileName);
                    int k = 0;
                    for (String str : uniq.uniq(input)) {
                        output.write(str);
                        if (k < uniq.uniq(input).size())
                            output.write("\n");
                    }
                    output.close();
                } catch (IOException e) {
                    System.out.println("Error writing file");
                }
            } else {
                for (String str : uniq.uniq(input)) {
                    System.out.println(str);
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}