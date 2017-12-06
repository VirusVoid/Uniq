import Uniq.*;

import java.io.*;

public class Tests {

    public static void main(String[] args) {
        test();
    }

    public static void test() {

        int passedTests = 0;

        String[] args1 = new String[3];
        args1[0] = "-o";
        args1[1] = "ActualOut/actualOut1";
        args1[2] = "Input/input1";
        UniqLauncher.main(args1);
        File out1 = new File("Output/out1");
        File actualOut1 = new File("ActualOut/actualOut1");

       /* String[] args2 = new String[5];
        args2[0] = "-i";
        args2[1] = "-c";
        args2[2] = "-o";
        args2[3] = "test\\actualOut2";
        args2[4] = "test\\input2";
        UniqLauncher.main(args2);
        File out2 = new File("test\\out2");
        File actualOut2 = new File("test\\actualOut2");

        String[] args3 = new String[4];
        args3[0] = "-c";
        args3[1] = "-o";
        args3[2] = "tests\\actualOut\\actualOut3";
        args3[3] = "tests\\Input\\input3";
        UniqLauncher.main(args3);
        File out3 = new File("tests\\Out\\out3");
        File actualOut3 = new File("tests\\actualOut\\actualOut3");

        String[] args4 = new String[5];
        args4[0] = "-s";
        args4[1] = "3";
        args4[2] = "-o";
        args4[3] = "tests\\actualOut\\actualOut4";
        args4[4] = "tests\\Input\\input4";
        UniqLauncher.main(args4);
        File out4 = new File("tests\\Out\\out4");
        File actualOut4 = new File("tests\\actualOut\\actualOut4");

        String[] args5 = new String[5];
        args5[0] = "-u";
        args5[1] = "-c";
        args5[2] = "-o";
        args5[3] = "tests\\actualOut\\actualOut5";
        args5[4] = "tests\\Input\\input5";
        UniqLauncher.main(args5);
        File out5 = new File("tests\\Out\\out5");
        File actualOut5 = new File("tests\\actualOut\\actualOut5");
*/
        if (compare(out1, actualOut1)) passedTests++;
       /* if (compare(out2, actualOut2)) passedTests++;
        if (compare(out3, actualOut3)) passedTests++;
        if (compare(out4, actualOut4)) passedTests++;
        if (compare(out5, actualOut5)) passedTests++;*/

        System.out.println("Tests passed:" + passedTests);
    }

    private static boolean compare(File file1, File file2) {

        try {

            BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));

            String str1 = reader1.readLine();
            String str2 = reader2.readLine();

            if (str1 == null && str2 == null) return true;
            if (str1 == null || str2 == null) return false;

            while (str1 != null && str2 != null) {
                if (!str1.equals(str2)) return false;
                str1 = reader1.readLine();
                str2 = reader2.readLine();
            }

            if (str1 == null && str2 == null) return true;
            if (str1 == null || str2 == null) return false;

        } catch (IOException e) {
            System.out.println("Error comparing file \"" + file1.getPath() + "\"");
        }
        return false;
    }
}