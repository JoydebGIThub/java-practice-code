package coreJavaPractice.exceptions;

class MyUnCheckedException extends RuntimeException{
    public MyUnCheckedException(String message){
        super(message);
    }
}

public class DtryCatchMyUnchecked {
    public static void main(String[] args) {
        try {
            validMarks(-4);
        } catch (MyUnCheckedException e) {
            System.out.println("Caught Custom Unchecked Exception: "+e.getMessage());
        }
    }

    public static void validMarks(int marks) throws MyUnCheckedException{
        if(marks<0){
            throw new MyUnCheckedException("Marks can't be negative");
        }
        System.out.println("Valid marks: "+marks);
    }
}
