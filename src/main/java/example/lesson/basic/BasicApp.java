package example.lesson.basic;

import java.util.Scanner;

public class BasicApp {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String name;
        do {
            name = reader.nextLine();
            printSplitted(name);
            System.out.println(reverse(name));
        } while(!"end".equals(name));
    }

    /**
     * "Poznań"
     * "P"
     * "o"
     * "z"
     * "n"
     * "a"
     * "ń"
     *
     */
    private static void printSplitted(String name) {
        for (int i = 0; i < name.length(); i++) {
            System.out.println(name.charAt(i));
        }

    }


    public static String reverse(String givenName) {
        // --> create your solution
        /***
         *
         * "Poznań"
         *
         * "P" + ""
         * "o" + "P"
         * "z" + "oP"
         * "n" + "zoP"
         * "a" + "nzoP"
         * "ń" + "anzoP"
         * "ńanzoP"
         *
         * for (...) {
         *     result  = i-th character of givenName + result;
         * }
         * return result;
         *
         */
        String result = "";

        for (int i = 0; i < givenName.length(); i++) {
            result = givenName.charAt(i) + result;
        }

        return result;
    }
}
