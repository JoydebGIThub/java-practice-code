package coreJavaPractice.threads;

class SleepThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <= 3; i++) {
            // try {
            //  Thread.sleep(5000);
               
            // } catch (InterruptedException e) {
            //     System.out.println(e.getMessage());
            // }
            System.out.println(i); 
        }
    }
}

public class Ethread4 {
    public static void main(String[] args) {
        SleepThread t= new SleepThread();
        SleepThread t2= new SleepThread();
        t.start();
        t2.start();
    }
}
