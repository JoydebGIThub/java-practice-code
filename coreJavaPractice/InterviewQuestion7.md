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
****
****
## Q: What is a deadlock? How can you avoid it in Java?
### What is a Deadlock?
Imagine a busy intersection in a city like Bangalore. If two cars enter the intersection from different directions and block each other's path, neither can proceed. They are in s "standstill"- a deadlock.
In the context of multithreading, a "deadlock" occurs when two or more threads are blocked indefinitely, waiting for each other to release the resources(locks) that they need to proceed. Each thread wants, and neither is willing to give up what they have.
#### The Four Necessary Conditions for a Deadlock (Coffman Conditions):
For a deadlock to occure, all four of the following conditions must be true simultaneously:
1. Mutual Exclusion: Resources involved must be non-shareable. Only one thread can hold a particular resource at a time (Think of a single key to a specific lock).
2. Hold and Wait: A thread must hold at least one resource and be waiting to acquire one or more other resources held by other threads. (Like a car stopped in the intersection, blocking another while waiting for its own path to clear)
3. No Preemption: Resources cannot be forcibly taken away from a thread that is holding them. A resource can only be released voluntarily by the thread holding it. (You can't just magically move the cars out of the intersection; they need to move themselves).
4. Circular wait: There must exist a circular chain of two or more threads, where each thread is waiting for a resource held by the next thread in the chain. (Car A is blocking Car B, and Car B is blocking Car A, forming a circle of waiting).
#### How Deadlocks Manifest in Java(Coding Examples):
```java
public class Deadlock{
   private static final Object resource1= new Object();
   private static final Object resource2= new Object();

   public static void main(String[] args){
      Thread t1= new Thread(()->{
         synchronized(resource1){
            System.out.println(Thread.currentThread().getName()+ " acquired lock on resource1.");
            try{
               Thread.sleep(100);//Simulate some work
            }catch(InterruptedException e){
               Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName()+" is waiting to acquire lock on resource2...");
            synchronized(resource2){
               System.out.println(Thread.currentThread().getName()+" acquired lock on resource2.");
               // Critical section accessing both resources
            }
            System.out.println(Thread.currentThread().getName()+" released lock on resource2.");
         }
         System.out.println(Thread.currentThread().getName()+" released lock on resource1.");
      }, "Thread-1");

      Thread t2= new Thread(()->{
         synchronized(resource2){
            System.out.println(Thread.currentThread().getName()+ " acquired lock on resource2.");
            try{
               Thread.sleep(100);//Simulate some work
            }catch(InterruptedException e){
               Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName()+" is waiting to acquire lock on resource1...");
            synchronized(resource1){
               System.out.println(Thread.currentThread().getName()+" acquired lock on resource1.");
               // Critical section accessing both resources
            }
            System.out.println(Thread.currentThread().getName()+" released lock on resource1.");
         }
         System.out.println(Thread.currentThread().getName()+" released lock on resource2.");
      }, "Thread-2");
      t1.start();
      t2.start();
   }
}
```
#### Output:
```
Thread-1 acquired lock on resource1.
Thread-2 acquired lock on resource2.
Thread-2 is waiting to acquire lock on resource1...
Thread-1 is waiting to acquire lock on resource2...
```
#### Scenario Leading to Deadlock:
1. "Thread-1" acquires the lock on "resource1";
2. "Thread-2" acquires the lock on "resource2";
3. "Thread-1" now tries to acquire the lock on "resource2", but it's held by "Thread-2", so "Thread-1" blocks.
4. "Thread-2" now tries to acquire the lock on "resource1", but it's held by "Thread-2", so "Thread-2" blocks.

***
### How to Avoid Deadlock in Java:
The key to avoiding deadlocks is to break one or more of the four necessary conditions. Here are common strategies:
1. Avoid Circular Wait(Most common and effective strategy):
   - Acquire Locks in a Consistent Order: If all threads acquire the necessary locks in the same order, the circular wait condition cannot occur. In our example, if both threads always tried to acquire "resource1" first, and then "resource2", a deadlock would be unlikely.
     ```java
      public class AvoidDeadlockConsistentOrder{
         private static final Object resource1= new Object();
         private static final Object resource2= new Object();
           public static void main(String args[]){
               Thread t1= new Thread(()->acquireInOrder(resource1, resource2),"Thread-1");
               Thread t2= new Thread(()->acquireInOrder(resource1, resource2),"Thread-2");

               t1.start();
               t2.start();
           }
           public static void acquireInOrder(Object firstLock, Object secondLock){
               synchronized(firstLock){
                  System.out.println(Thread.currentThread().getName() + " acquired lock on "+firstLock);
                  try{
                     Thread.sleep(100);
                  }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                  }
                  synchronized(secondLock){
                     System.out.println(Thread.currentThread().getName() + " acquired lock on "+secondLock);
                  }
                  System.out.println(Thread.currentThread().getName() + " released lock on "+secondLock);
               }
              System.out.println(Thread.currentThread().getName() + " released lock on "+firstLock);
           }
     }
     ```
2. Limit Holding and Waiting:
   - Acquire All necessary Locks at Once: Try to acquire all the locks a thread needs before it starts its task. If a thread cannot acquire all the locks, it should release any locks it currently holds and try again later.
     ```java
         import java.util.concurrent.locks.Lock;
         import java.util.concurrent.locks.ReentrantLock;
         public class AvoidDeadLock{
            public static final Lock lock1= new ReentrantLock();
            public static final Lock lock2= new ReentrantLock();

            public static void main(String args[]){
               Thread t1= new Thread(()->tryAcquireBoth(lock1, lock2), "Thread-1");
               Thread t2= new Thread(()->tryAcquireBoth(lock2, lock1), "Thread-2");// different acquisition order initially
               t1.start();
               t2.start();
            }
            public static void tryAcquireBoth(Lock firstLock, Lock secondLock){
               while(true){
                  boolean firstAcquired = false;
                  boolean secondAcquired = false;

                  try{
                        firstAcquired = firstLock.tryLock();
                        secondAcquired = secondLock.tryLock();
                  }finally{
                     if(firstAcquired && secondAcquired){
                          System.out.println(Thread.currentThread().getName()+" acquired both locks.");
                          lock2.unlock();
                          lock1.unlock();
                          break;
                     }if(firstAcquired){
                           lock1.unlock();
                     }if(secondAcquired){
                           lock2.unlock();
                     }

                     try{
                        Thread.sleep(100);
                     }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                          break;
                     }
                  }
               }
            }
         }
     ```
     - Using: "java.util.concurrent.locks.Lock" and its "tryLock()" method allows a thread to attempt to acquire a lock without blocking indefinitely. If it can't get all the necessary locks, it can release the ones it holds and try again.
3. Allow Preemption(Less Common with Standard synchronized):
   - With standard "synchronized", preemption is not directly supported. However the "java.util.concurrent.locks.Lock" interface provides mechanisms(through complex to implement correctly) that could potentially allow for some form of preemption. This typically involves setting timeout on lock acquisition attempts.
4. Avoid Hold and Wait:
   - Request All resources at the beginning: A thread should request all the resources it needs before starting its execution. If any resource is unavailable, the thread should not acquired any resources and should try again later.
