package coreJavaPractice.exceptions;

public class DtryCatchNumberFormat {
    public static void main(String[] args) {
        String str="abc";

        try {
            int number = Integer.parseInt(str);
            System.out.println("Number: "+number);
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException: "+e.getMessage());
        }
    }
}
