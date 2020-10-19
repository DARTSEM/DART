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
    public static double doubleInputNoNegative(String error) {

        double value = doubleInput();
        while (value < 0) {
            System.out.println("Invalid data. " + error);
            value = doubleInput();
        }
        return value;
    }
    //EDIT1
    public static double InputRenderNoNegative() {

        double value = doubleInput();
        while (value < 0 && value == value){
            System.out.println("Invalid operation. ");
            value = doubleInput();
        }
       return value;
    }

    public static String stringInput() {
        return scan.nextLine();
    }

    public static String stringInputNoEmpty(String error) {

        String text = stringInput();
        while (text.isEmpty()) {
            System.out.println("Invalid data. " + error);
            text = stringInput();
        }
        return text;
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



