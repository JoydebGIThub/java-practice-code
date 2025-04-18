package coreJavaPractice.exceptions;

public class DtryCatchArithmetic {
    public static void main(String[] args) {
        try {
            System.out.println("The result: "+checked(10, 0));

        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
    public static int checked(int a, int b) throws ArithmeticException{
        int result = a/b;
        System.out.println("Result is: "+result);
        return result;
    }
}
