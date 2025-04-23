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
#### Important Note: 
For instance methods (non-static), "synchronized" accquires the lock on the "specific object instance" (counter in this case). Different "Counter" objects would have their own independent locks.
#### Synchronized Static Methods:
If a method is "static" and "synchronized", the lock acquired is on the "Class" object itself (Counter.class). These means only one thread can execute any synchronized static method of the "Counter" class at a time, across all instances of "Counter".

2. Synchronized Block:
   -
   Sometimes, you don't need to synchronize the entire method; you might just need to protect a specific "critical section of code" within a method. You can achive this using a "synchronized" block.
```java
class SharedResource{
   private StringBuilder buffer= new StringBuilder();
   public void appendData(String data){
      //Some non-critical operation
      System.out.println(Thread.currentThread().getName() + " is about to append: "+data);
      try{
         Thread.sleep(10); //Simulate some work
      }catch(InterruptedException e){
         Thread.currentThread().interrupt();
      }

      //Synchronized block  to protect access to the shared buffer
      synchronized(buffer){
         buffer.append(data);
         System.out.println(Thread.currentThread().getName()+" appended: "+data+", current buffer: "+ buffer);
         try{
            Thread.sleep(5); //Simulate writing to buffer
         }catch(InterruptedException e){
            Thread.currentThread().interrupt();
         }      
      }
   System.out.println(Thread.currentThread().getName()+" finished processing: "+data);
   }
   public String getBufferContent(){
      return buffer.toString();
   }
}

public class SynchronizedBlock{
   public static void main(String args[]) throws InterruptedException{
      SharedResource resource =  new SharedResource();

      Runnable task=()->{
         resource.appendData(Thread.currentThread().getName());
      };

      Thread tA= new Thread(task, "Thread-A");
      Thread tB= new Thread(task, "Thread-B");
      Thread tC= new Thread(task, "Thread-C");

      tA.start();
      tB.start();
      tC.start();

      tA.join();
      tB.join();
      tC.join();
      System.out.println("\nFinal buffer content: "+resource.getBufferContent());
   }
}
```
#### Output:
```
Thread-C is about to append: Thread-C
Thread-B is about to append: Thread-B
Thread-A is about to append: Thread-A
Thread-C appended: Thread-C, current buffer: Thread-C
Thread-C finished processing: Thread-C
Thread-B appended: Thread-B, current buffer: Thread-CThread-B
Thread-B finished processing: Thread-B
Thread-A appended: Thread-A, current buffer: Thread-CThread-BThread-A
Thread-A finished processing: Thread-A

Final buffer content: Thread-CThread-BThread-A
```
#### Explanation:
- The "appendData()" method has a "synchronized" block that uses "buffer" as the "monitor object". This means that only one thread can execute the code inside the "synchronized (buffer){.....}" block at any time.
- When a thread reaches this block, it tries to acquire the monitor lock of the "buffer" object. If another thread is already inside a synchronized block or method that also uses the "buffer" object's lock, the current thread will wait.
- The non-synchronized parts of the "appendData()" method can be executed concurrenthly by muttiple threads.
- The approach allows for finer-grained control over synchronization, potentially improving performance by only synchronizing the critical sections.
While "synchronized" is essential for thread safety, it comes with a performance cost.
1. Blocking: When a thread tries to enter a synchronized section and the lock is already held by another thread, the first thread gets "blocked". Blocked threads are put into a waiting state by the "operating system", which invloves "overhead" for "suspending" and "resuming" threads.
2. Context Switching: When a blocked thread is finally allowed to acquire the lock(because the owning thread released it), the operating system needs to perform a "context switch". This invloves "saving the state" of the "previously running thread" and loading the state of the newly unblocked thread, which consumes CPU time.
3. Lock Management Overhead: The JVM has "internal mechanisms" for managing locks (acquiring, releasing, monitoring). These operations themselves take some processing time.
4. Reduced Concurrency: By its very nature, "synchronized" limits concurrency on the protected resources. Only one thread can proceed at a time, even if other parts of the application could potentially run in parallel. This can become a "bottleneck" in highly concurrent application.
#### Modern JVM Optimizations:
It's important to note that modern JVMs have implemented various optimizations to reduce the performace overhead of "synchronized" in many common scenarios:
- Biased Locking: If a lock is mostly accessed by a single thread, the JVM can "bias" the lock towards that thread, significantly reducing the overhead for subsequent acquisitions by the same thread.
- Lock Elision: If the JVM can determine that a lock is only ever accessed by a single thread(even if the "synchronized" keyword is used), it might completely eliminate the locking operations.
- Adaptive Spinning: When a thread tries to acquire a lock held by another thread, instead of immediately blocking, it might briefly "spin" (repeatedly check if the lock becomes avaiable). This can be more efficient if the lock is released quickly.
- Lock Coarsening: If there are multiple consecutive synchronized blocks on the same object, the JVM might merge them into a single larger synchronized block to reduce the overhead or repeated lock acquisition and release.
