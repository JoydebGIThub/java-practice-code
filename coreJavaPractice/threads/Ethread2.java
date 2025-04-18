package coreJavaPractice.threads;
class MyThreadRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread using Runnable interface");
    }
    
}
public class Ethread2 {
    public static void main(String[] args) {
        MyThreadRunnable run= new MyThreadRunnable();
        Thread t= new Thread(run);
        t.start();
    }
}
