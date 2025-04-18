package coreJavaPractice;

public class DtryCatchStringIndex {
    public static void main(String[] args) {
        String text="Java";

        try {
            System.out.println(text.charAt(10));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Caught StringIndexOutOfBoundsException: "+e.getMessage());
        }
    }
}
