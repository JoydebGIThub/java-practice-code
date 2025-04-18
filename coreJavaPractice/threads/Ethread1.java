package coreJavaPractice.threads;

class MyThread extends Thread{
    public void run(){
        System.out.println("Thread is running");
    }
}

public class Ethread1 {
    public static void main(String[] args) {
        MyThread thread= new MyThread();
        thread.start();
    }
}
