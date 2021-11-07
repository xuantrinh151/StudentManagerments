package validation;

import java.util.ArrayList;
import java.util.Scanner;

public class Validation {

    private final static Scanner in = new Scanner(System.in);

    public static int checkInputIntLimit(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    public static String checkInputString(String inputMsg, String errorMsg, String regex) {
        String input;
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            input = in.nextLine().trim().toUpperCase();
            match = input.matches(regex);
            if (match == false) {
                System.out.println(errorMsg);
            } else {
                return input;
            }
        }
    }

    public static String inputID() {
        String id = checkInputString("Input id(HEXXXXXX): ",
                "The format of id is HEXXXXXX (X stands for a digit)", "\\b(HE)[0-9]{6}\\b");
        return id;
    }

    public static String inputName() {
        String name = checkInputString("Enter Name: ", "Please enter student name!", "[a-zA-Z ]+");
        return name;
    }

    public static String inputSemester() {
        String semester = checkInputString("Enter semester: ", "Please enter semester!", "[a-zA-Z0-9 ]*+");
        return semester;
    }

    public static String checkInputCourse() {
        while (true) {
            String result = in.nextLine().trim();
            if (result.equalsIgnoreCase("Java")
                    || result.equalsIgnoreCase(".NET")
                    || result.equalsIgnoreCase("C/C++")) {
                return result;
            }
            System.err.println("There are only three courses: Java, .Net, C/C++");
            System.out.print("Enter again: ");
        }
    }

    public static boolean checkInput(String a, String b, String c) {
        while (true) {
            String result = in.nextLine().trim();
            if (result.equalsIgnoreCase(a)) {
                return true;
            } else if (result.equalsIgnoreCase(b)) {
                return false;
            }
            System.err.println(c);
            System.out.print("Enter again: ");
        }
    }

    public static boolean checkInputYN() {
        return checkInput("Y", "N", "Please input y/Y or n/N");
    }

    public static boolean checkInputUD() {
        return checkInput("U", "D", "Please input u/U or d/D");
    }
}
