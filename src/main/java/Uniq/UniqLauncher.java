package main.java.Uniq;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;

public class UniqLauncher {
    @Argument(metaVar = "file", usage = "Input file name")
    private String inputFileName;

    @Option(name = "-o", metaVar = "ofile", usage = "Output file name")
    private String outputFileName;

    @Option(name = "-i", metaVar = "insensetiveCase", usage = "Insensitive case")
    private boolean insensetiveCase;

    @Option(name = "-s", metaVar = "IgnoreNSymbols", usage = "N symbols ignoring")
    private int ignoreNSymbols;

    @Option(name = "-u", metaVar = "uniqueStrings", usage = "Unique strings")
    private boolean uniqueStrings;

    @Option(name = "-c", metaVar = "numberOfEqualStrings", usage = "Number of equal strings")
    private boolean numberOfEqualStrings;

    public static void main(String[] args) {
        new UniqLauncher().launch(args);
    }

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Uniq.jar -i -u -c -s num -o ofile file");
            parser.printUsage(System.err);
            return;
        }
        Uniq uniq = new Uniq(inputFileName, outputFileName, insensetiveCase, ignoreNSymbols, uniqueStrings, numberOfEqualStrings);
        try {
            InputStream in;
            OutputStream out;
            if (inputFileName != null) {
                in = new FileInputStream(inputFileName);
            } else {
                in = new BufferedInputStream(System.in);
            }
            if (outputFileName != null) {
                out = new FileOutputStream(outputFileName);
            } else {
                out = new BufferedOutputStream(System.out);
            }
            uniq.uniq(in, out);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}