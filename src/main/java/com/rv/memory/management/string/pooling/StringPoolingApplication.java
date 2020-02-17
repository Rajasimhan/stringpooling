package com.rv.memory.management.string.pooling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class StringPoolingApplication {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        SpringApplication.run(StringPoolingApplication.class, args);

        System.out.println();
        String string1 = "string1";
        List<String> arrayListOfString = Arrays.asList("string1", string1);

        System.out.println(ANSI_BLUE + "1) In the following example all returns true as string1 and string2 are created by jvm using string pooling");
        String string2 = "s" + "t" + "r" + "i" + "n" + "g" + "1";
        printResult(string1, string2, arrayListOfString);

        System.out.println(ANSI_BLUE + "2) In the following example all returns true as string1 was assigned a value from the pool");
        string1 = "string1";
        printResult(string1, string2, arrayListOfString);

        System.out.println(ANSI_BLUE + "3) In the following example all returns true as the modifyString() method returned string value from the pool");
        string1 = modifyString();
        printResult(string1, string2, arrayListOfString);

        System.out.println(ANSI_BLUE + "4) In the following example all returns true except the one which compares string1==string2 " +
                "as string2 was constructed using concat and substring which looks like created a new string and didn't use string pooling " +
                "contrary to what we saw in example 1");
        string2 = string1.substring(0, 1) + string1.substring(1, 2) + string1.substring(2, 3) + string1.substring(3, 7);
        printResult(string1, string2, arrayListOfString);

        //rollback string2 to previous value for the next example
        string2 = "string1";
        System.out.println(ANSI_BLUE + "5) In the following example all == comparisons returns false as string1 is created using new String(\"string1\") " +
                "which will create a new instance of string and not use pool");
        string1 = new String("string1");
        printResult(string1, string2, arrayListOfString);

        System.out.println(ANSI_BLUE + "6) In this example all comparisons returns false as the strings are different");
        string1 = "string1 is modified";
        printResult(string1, string2, arrayListOfString);

        //rollback string1 to previous value for the next example
        string1 = "string1";
        System.out.println(ANSI_BLUE + "7) In this example all comparisons returns true except the one comparing arrayListOfString(1) as the strings are different");
        arrayListOfString.set(1, "string1 is modified");
        printResult(string1, string2, arrayListOfString);

    }

    private static String modifyString() {
        return "string1";
    }

    private static void printResult(String string1, String string2, List<String> arrayListOfString) {
        System.out.println();
        System.out.printf(ANSI_RESET + "string1 is: %s\n", ANSI_GREEN + string1);
        System.out.printf(ANSI_RESET + "string2 is: %s\n", ANSI_GREEN + string2);
        System.out.printf(ANSI_RESET + "arrayListOfString is: %s\n", ANSI_GREEN + arrayListOfString);
        System.out.printf(ANSI_RESET + "is string1==string2 ? %s\n", printColoredResult(string1 == string2));
        System.out.printf(ANSI_RESET + "is string1.equals(string2) ? %s\n", printColoredResult(string1.equals(string2)));
        System.out.printf(ANSI_RESET + "is string1 == arrayListOfString.get(0) ? %s\n", printColoredResult(string1 == arrayListOfString.get(0)));
        System.out.printf(ANSI_RESET + "is string1.equals(arrayListOfString.get(0) ? %s\n", printColoredResult(string1.equals(arrayListOfString.get(0))));
        System.out.printf(ANSI_RESET + "is string1 == arrayListOfString.get(1) ? %s\n", printColoredResult(string1 == arrayListOfString.get(1)));
        System.out.printf(ANSI_RESET + "is string1.equals(arrayListOfString.get(1)) ? %s\n", printColoredResult(string1.equals(arrayListOfString.get(1))));
        System.out.println();
    }

    private static String printColoredResult(boolean result) {
        return result ? ANSI_GREEN + Boolean.toString(result) : ANSI_RED + Boolean.toString(result);
    }


}
