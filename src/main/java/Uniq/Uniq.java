package main.java.Uniq;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("WeakerAccess")
public final class Uniq {

    private String inputFileName;
    private String outputFileName;
    private boolean insensitiveCase;
    private int ignoreNSymbols;
    private boolean uniqueStrings;
    private boolean numberOfEqualStrings;

    public Uniq(String inputFile, String outputFile, boolean insensitiveCase,
                int ignoredNSymbols, boolean uniqueStrings, boolean numberOfEqualStrings) {

        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.insensitiveCase = insensitiveCase;
        this.ignoreNSymbols = ignoredNSymbols;
        this.uniqueStrings = uniqueStrings;
        this.numberOfEqualStrings = numberOfEqualStrings;
    }

    public void uniq(InputStream in, OutputStream out) throws IOException {
        PrintWriter output = new PrintWriter(out);
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(in)) {
            String str;
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                strings.add(str);
            }
            if ((!uniqueStrings) && (!numberOfEqualStrings)) {
                result.addAll(strings);
            } else {
                strings.add("0");
                int[] numEqual = new int[strings.size()];
                numEqual[strings.size() - 1] = 1;
                for (int i = 0; i < strings.size() - 1; i++) {
                    numEqual[i] = 1;
                    String str1 = strings.get(i);
                    String str2 = strings.get(i + 1);
                    int comparison = 0;
                    if (insensitiveCase) {
                        if (str1.equalsIgnoreCase(str2))
                            comparison++;
                    }
                    if (ignoreNSymbols > 0) {
                        str1 = str1.substring(ignoreNSymbols);
                        str2 = str2.substring(ignoreNSymbols);
                        if (str1.equals(str2))
                            comparison++;
                    }
                    if (uniqueStrings) {
                        if (str1.equals(str2))
                            comparison++;
                    }
                    if (comparison > 0) {
                        numEqual[i + 1] = numEqual[i] + 1;
                    } else {
                        if (numberOfEqualStrings) {
                            str1 = numEqual[i] + str1;
                        }
                        result.add(str1);
                        output.println(result.get(i));
                    }
                }
            }
        }
        output.close();
    }
}