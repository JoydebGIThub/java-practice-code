package coreJavaPractice.exceptions;

public class DtryCatchNullpointer {
    public static void main(String[] args) {
        String str=null;
        try {
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: "+e.getMessage());
        }
    }
}
