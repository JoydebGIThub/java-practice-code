package coreJavaPractice.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ethread8 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i <=5; i++) {
            executor.submit(()->{
                System.out.println("Thread "+Thread.currentThread().getName()+" is running");
            });
        }
        executor.shutdown();
    }
}
