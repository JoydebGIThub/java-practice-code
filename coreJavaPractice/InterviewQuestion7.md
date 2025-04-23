# Interview Therory Question:
## Q: Explain how synchronized works. How does it affect performance?
### The Problem: Shared Resources and Race conditions:
Imagine you have a bank account with a certain balance, and two people (two threads in a program) try to withdraw money from it simultaneously.
If not handled carefully, you could end up with a situation where the final balance is incorrect - maybe both withdrawals are processed based on the initial balance, leading to an overdraft that shouldn't have happened.
This kind of issue, where the outcome of a program depends on the unpredictable order of executing of multiple threads accessing "shared resources" is called a "race condition".

The "synchronized" keyword in Java's way of putting rules in place to prevent these "chaotic situations". It's like a "traffic controller" for threads trying to access the same piece of information.
### The Solution: "synchronized" - Putting a Lock on Things:
The core idea of "synchronized" is to allow only one thread to access a specific piece of code or a specific object at any given time. It achieves this by using the concept of a "monitor" (also known as an intrinsic lock or object lock) associated with every object in Java.
Think of a monitor like a "single key" that can open a door to a "critical section of your code" or specific object's data. Only the thread holding the key can enter. Other threads have to wait their turn.
#### Two ways to Use "synchronized":
1. Synchronized Methods:
   -
   When you declare an entire method as "synchronized", the key (the monitor lock) is automatically acquired when a thread enters the method and automatically released when the thread "exits" the method (either normally or by throwing an exception).
   ```java
    class Counter{
      private int count = 0;
      //Make the entire method synchronized
      public synchronized void increment(){
          count++;
          System.out.println(Thread.currentThread().getName() + " incremented count to: "+count);
      }
      public int getCount(){
          return count;
      }
    }
    public class SynchronizedMethod{
      public static void main(String[] args) throws InterruptedException{
        Counter counter= new Counter();
  
        Runnable task = () ->{
          for(int i=0; i< 1000; i++){
            counter.increment();
          }
        };
  
        Thread t1= new Thread(task, "Thread-1");
        Thread t2= new Thread(task, "Thread-2");
  
        t1.start();
        t2.start();

        t1.join(); //wait for t1 to finish
        t2.join(); // wait for t2 to finish
         System.out.println("Final count: "+counter.getCount());
        
      }
    }
   ```
#### Explanation:
- The "increment()" method in the "Counter" class is declared as "synchronized".
- When "Thread-1" calls "counter.increment()", it tries to acquire the monitor lock of the "counter" object. If no other thread holds the lock, "Thread-1" gets it, executes the "increment()" method, and then releases the lock.
- If "Thread-2" tries to call "counter.increment()" while "Thread-1" holds the lock, "Thread-2" will be blocked and will have to wait until "Thread-1" releases the lock.
- This ensures that the "counter++" operation is atomaic with respect to other synchronized methods on the same "counter" object, preventing race conditions. The final count will always be 2000
