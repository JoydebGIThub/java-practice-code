package coreJavaPractice.exceptions;

public class DtryCatchArithmetic2 {
    public static void main(String[] args) {
        int value = divide(10, 0);
        System.out.println(value);
    }   
    public static int divide(int a, int b){
        
        try {
            return a/b;
        } catch (Exception e) {
            return 299;
        }
    } 
}
