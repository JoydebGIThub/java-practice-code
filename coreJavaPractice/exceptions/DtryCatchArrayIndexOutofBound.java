package coreJavaPractice.exceptions;

public class DtryCatchArrayIndexOutofBound {
    public static void main(String[] args) {
        int[] array={1, 2, 3};

        try {
            System.out.println(array[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caugth ArrayIndexOutOfBoundsException: "+e.getMessage());
        }

    }
}
