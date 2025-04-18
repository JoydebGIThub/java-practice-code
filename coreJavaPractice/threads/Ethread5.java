package coreJavaPractice.threads;

class JoinThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <=1000; i++) {
            System.out.println("............"+i);
        }
    }
}

public class Ethread5 {
    public static void main(String[] args) throws InterruptedException {
        JoinThread t1= new JoinThread();
        JoinThread t2= new JoinThread();
        t1.start();
        t1.join();
        t2.start();
    }
}
