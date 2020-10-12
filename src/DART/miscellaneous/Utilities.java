package DART.miscellaneous;

import java.util.Scanner;

public class Utilities {
    private static Scanner scan = new Scanner(System.in);

    public static int intInput() {
        int value = 0;
        boolean correctInput = false;

        while (!correctInput) {
            try {
                value = scan.nextInt();
                correctInput = true;
            } catch (Exception wrongInput) {
                wrongInputRender("a real number without decimals.");
            }
            scan.nextLine();
        }
        return value;
    }

    public static double doubleInput() {
        double value = 0;
        boolean correctInput = false;

        while (!correctInput) {
            try {
                value = scan.nextDouble();
                correctInput = true;
            } catch (Exception wrongInput) {
                wrongInputRender("a real number.");
            }
            scan.nextLine();
        }
        return value;
    }

    public static String stringInput() {
        return scan.nextLine();
    }

    public static void closeScanner() {
        scan.close();
    }

    public static String line() {
        return "--------------------------------------------------------------------------------------------------------\n";
    }

    public static void wrongInputRender(String wrongInputInfo) {
        System.out.println("Your input was the wrong type, it must be " + wrongInputInfo + " Try again:");

    }
}

