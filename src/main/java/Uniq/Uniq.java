package main.java.Uniq;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("WeakerAccess")
public final class Uniq {

    private boolean insensitiveCase;
    private int ignoreNSymbols;
    private boolean uniqueStrings;
    private boolean numberOfEqualStrings;

   public Uniq(boolean insensitiveCase, int ignoreNSymbols, boolean uniqueStrings, boolean numberOfEqualStrings) {
        this.insensitiveCase = insensitiveCase;
        this.ignoreNSymbols = ignoreNSymbols;
        this.uniqueStrings = uniqueStrings;
        this.numberOfEqualStrings = numberOfEqualStrings;
    }

    public ArrayList<String> uniq(Reader in) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(in);
        int equal = 0;
        int num = 1;
        String str1;
        String str2 = br.readLine();
        String a;
        while (((a = br.readLine()) != null)) {
            str1 = str2;
            str2 = a;
            equal = 0;
            if (str1.equals(str2))
                equal++;
            if (insensitiveCase) {
                if (str1.equalsIgnoreCase(str2))
                    equal++;
            }
            if (ignoreNSymbols > 0) {
                if (str1.length() > ignoreNSymbols)
                    str1 = str1.substring(ignoreNSymbols);
                if (str2.length() > ignoreNSymbols)
                    str2 = str2.substring(ignoreNSymbols);
                if (str1.equals(str2))
                    equal++;
            }
            if (equal != 0) {
                num++;
            } else {
                add(str1, num, result);
                num = 1;
            }
        }
        add(str2, num, result);

        return result;
    }

    private void add(String str, int num, ArrayList<String> result) {
        if ((uniqueStrings) && (num == 1)) {
            result.add(str);
        }
        if (!uniqueStrings) {
            if (numberOfEqualStrings) {
                result.add(num + " " + str);
            } else result.add(str);
        }
    }
}