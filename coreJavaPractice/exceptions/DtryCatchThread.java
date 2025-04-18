package coreJavaPractice.exceptions;

public class DtryCatchThread {
    public static void main(String[] args) {
        try {
            System.out.println("Sleeping for 2 second");
            Thread.sleep(2000);
            System.out.println("Awake now");
        } catch (InterruptedException e) {
           System.out.println("InterruptedException Catch: "+e.getMessage());
        }
    }
}
