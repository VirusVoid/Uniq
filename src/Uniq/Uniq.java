package Uniq;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public final class Uniq {

    private boolean insensitiveCase;
    private int ignoreNSymbols;
    private boolean uniqueStrings;
    private boolean countStrings;

    public Uniq(boolean insensitiveCase, int ignoreNSymbols, boolean uniqueStrings, boolean countStrings) {
        this.insensitiveCase = insensitiveCase;
        this.ignoreNSymbols = ignoreNSymbols;
        this.uniqueStrings = uniqueStrings;
        this.countStrings = countStrings;
    }

    public List<String> uniq(Reader in) throws IOException {
        List<String> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(in);
        int num = 1;
        String str1;
        String str2 = br.readLine();
        String a;
        while (((a = br.readLine()) != null)) {
            str1 = str2;
            str2 = a;
            if (comparator(str1, str2)) {
                if (insensitiveCase) {
                    str2 = str2.toLowerCase();
                }
                num++;
            } else {
                add(str1, num, result);
                num = 1;
            }
        }
        add(str2, num, result);

        return result;
    }

    private void add(String str, int num, List<String> result) {
        if ((uniqueStrings)&&(num == 1)) {
            result.add(str);
        }
        if (!uniqueStrings) {
            if (countStrings) {
                result.add(num + " " + str);
            } else result.add(str);
        }
    }

    private boolean comparator(String str1, String str2) {
        boolean equal = false;
        if (str1.equals(str2))
            equal = true;
        if (insensitiveCase) {
            if (str1.equalsIgnoreCase(str2))
                equal = true;
        }
        if (ignoreNSymbols > 0) {
            if (str1.length() == str2.length()) {
                if (str1.regionMatches(ignoreNSymbols, str2, ignoreNSymbols, str1.length() - ignoreNSymbols)) {
                    equal = true;
                }
            }
        }
        return equal;
    }
}