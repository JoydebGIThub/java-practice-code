package coreJavaPractice.threads;
class Table{
    synchronized void printTable(int n){
        for (int i = 0; i < 100; i++) {
            System.out.println(n*i);
        }
    }
}

class MyThread1 extends Thread{
    Table t;
    MyThread1(Table t){
        this.t=t;
    }
    @Override
    public void run() {
        t.printTable(5);
    }
}

class MyThread2 extends Thread{
    Table t;
    MyThread2(Table t){
        this.t=t;
    }
    @Override
    public void run() {
        t.printTable(100);
    }
}
public class Ethread6 {
    public static void main(String[] args) {
        Table obj = new Table();
        new MyThread1(obj).start();
        new MyThread2(obj).start();
    }
}
