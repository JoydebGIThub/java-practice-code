package coreJavaPractice.exceptions;

class InvalidAgeException extends Exception{
    public InvalidAgeException(String message){
        super(message);
    }
}

public class DtryCatchCustom {
    public static void main(String[] args) {
        try {
            validAge(16);
        } catch (InvalidAgeException e) {
            // e.printStackTrace();
            System.out.println("Custom exception: "+e.getMessage());
        }
        
    }

    public static void validAge(int age) throws InvalidAgeException{
        if (age<18) {
            throw new InvalidAgeException("Age must be at least 18");
        }
        System.out.println("Access Granted");
    }
}
