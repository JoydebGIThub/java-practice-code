package coreJavaPractice.threads;

class MultiThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " is running");
    }
    
}

public class Ethread3 {
 public static void main(String[] args) {
    for (int i = 0; i <=3; i++) {
        Thread t = new Thread(new MultiThread(), "Thread-"+i);
        t.start();
    }
 }
    
}