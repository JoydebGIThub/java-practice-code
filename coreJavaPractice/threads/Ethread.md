# Thread
```
    1. We extends the Thread class to override the run method to use the thread in our code.
    2. We create the instance of the class which extends the Thread class.
    3. using .start() method we start the thread.
    4. For starting the thread the run method must be present in the thread class
    5. Thread share same memory space
```
***
***
## Basic Thread using Thread Class:

```java

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

```
### Output:
```
Thread is running
```

## Thread Using Runnable interface
```
    * Runnable is a interface
    * Thread Class also implements Runnable
    * To execute the run method in the implemented Runnable class we need to pass the implemented Runnable class instance in the Thread object as an arguments
```
```java
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

```
### Output:
```
Thread using Runnable interface
```

## Creating Multiple Thread

```java
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
```

### Output:
```
Thread-2 is running
Thread-0 is running
Thread-3 is running
Thread-1 is running
```

#### Explanation:
```
    * Everytime the Output will be different.

 Thread t = new Thread(new MultiThread(), "Thread-"+i); 
    * By using this we can send a string so that we can get the current thread name

Thread.currentThread().getName()
    * By using this we can print that String name 
```

## Thread Sleep
```
    1. The .sleep() method in Java is used with threads to pause the execution of the current thread for a specific amount of time.
    2. When Thread.sleep(time) is called, it pauses the execution of the current thread for the specified number of milliseconds (and optionally nanoseconds).
    3. The thread goes into a "Timed Waiting" state.
    4. After the sleep time has elapsed, the thread becomes Runnable again and waits for the CPU to schedule it.
    5. Thread.sleep() does not release locks it holds.
    6. It throws an InterruptedException, which must be handled using a try-catch block.
```
***
```java
package coreJavaPractice.threads;

class SleepThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <= 3; i++) {
            try {
             Thread.sleep(5000);
             System.out.println(i);   
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
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

```
### Output:
```
0
0
1
1
2
2
3
3
```
#### Without the sleep the output will be
```
0
1
2
3
0
1
2
3
```
## Thread Join
```java
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

```
### Output:
```
If we run the above code without .join() then after t1 run for sometimes t2 execution will start without even completed the t1 and vice versa

But when we use t1.join(), t2 will wait for t1 to finish
```

## Thread Synchronization
```java
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

```
#### Explanation:
```
    1. when multiple threads access shared resource like "*printTable method*" its possible for data correcption or other problems to occure.
    This is known as a **race condition**.
    2. To prevent race condition to avoid we use "synchronize access" to shared resources.
    3. When we declared a method as "synchronized", only one thread can execute that method on a given object "at a time".
    
```
***