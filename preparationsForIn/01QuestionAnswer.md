## Different ways to create threads
Ans: There are primarily two fundamental ways to create threads in Java:
1.  **Implementing the Runnable interface**: You define a class that **implements the Runnable interface**. This interface has a **single method, run()**, where you put the code that you want the thread to execute. You then create a Thread object, passing an **instance of your Runnable class** to its constructor, and finally call the start() method on the Thread object. This approach is generally preferred as it allows your class to extend another class if needed, promoting better code organization and adhering to the principle of "favoring composition over inheritance."
```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable thread is running");
    }
}

public class RunnableExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}
````
2. **Extending the Thread class**: You can create a class that extends the **Thread** class and **override its run()** method. You then create an instance of your subclass and call its start() method. While this is a valid way to create threads, it's less flexible because your class cannot extend any other class.
```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread class thread is running");
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
```
While these are the two primary ways, with the introduction of the Concurrency Utilities in Java 5 (specifically the java.util.concurrent package), we have more sophisticated and often preferred ways to manage threads using:
3. **ExecutorService**: This interface provides a framework for **managing and executing threads**. You can use factory methods in the Executors class (like **newFixedThreadPool(), newCachedThreadPool(), newSingleThreadExecutor(), etc.**) to create different kinds of **thread pools**. This is a higher-level abstraction that simplifies thread management and offers better control over thread lifecycle and resource utilization.
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> System.out.println("Task executed by ExecutorService"));
        executor.shutdown();
    }
}
```
****************************************************************************************************
## Q: What is the recomending ways to create threads?
Ans: "While you can create threads by **implementing Runnable or extending Thread**, the recommended way, especially in Spring Boot and microservices, is to use the **java.util.concurrent.ExecutorService**. It offers better **resource management through thread pooling**, decouples task submission from execution, and provides **more control over thread lifecycle**, leading to more efficient and maintainable concurrent applications."
*****************************************************************************************************
## Q: What is deadlock and how we can avoid it?
Ans: A deadlock is a **situation in concurrent programming** where **two or more threads are blocked indefinitely**, waiting for each other to **release resources** that they need. Imagine two trains stuck on a single track, each waiting for the other to move. Neither can proceed.
### How to Avoid Deadlock?
Preventing deadlocks involves carefully designing your concurrent system to break at least one of the four necessary conditions for a deadlock to occur (known as Coffman's conditions):
1. **Mutual Exclusion**: Resources involved must be non-shareable; only one thread can use a resource at a time. We often can't eliminate this condition as many resources inherently require exclusive access (like database locks or file handles).
2. **Hold and Wait**: A thread holds onto at least one resource while waiting to acquire other resources. This is a key condition we can often target.
3. **No Preemption**: Resources cannot be forcibly taken away from a thread holding them; they must be released voluntarily by the thread.
4. **Circular Wait**: Two or more threads form a circular chain where each thread waits for a resource held by the next thread in the chain.
- Breaking Hold and Wait:
  - **Acquire all necessary locks at once**: A thread should attempt to acquire all the locks it needs before proceeding. If it can't acquire all of them, it should release any it has already acquired and retry. This can be achieved using methods like tryLock() with a timeout.   
  - **Request resources only when needed and release them promptly**: Minimize the time a thread holds multiple locks.
- Breaking Circular Wait:
  - **Establish a global ordering of locks**: Assign a unique order to all the locks in the system. Threads should acquire locks in this specific order. If a thread needs multiple locks, it should always acquire them in the defined sequence. This prevents the circular dependency. For example, if you have locks A and B, all threads should try to **acquire A before B**.
- Breaking No Preemption (Less Common in Standard Locking):
  - **Use tryLock() with a timeout**: If a thread cannot acquire a lock within a certain time, it can release any locks it currently holds and try again later. This introduces a form of preemption by the thread itself.
  -**Consider alternative synchronization mechanisms**: Some advanced concurrency constructs might allow for preemption, but this is less common with standard synchronized blocks and ReentrantLock.
***************************************************************************************************************
## Q: What are the different state which the thread can be present
1. **NEW**: A thread is in the **NEW state** when it has been **created but has not yet started**. At this point, no system resources have been allocated to the thread. You've created a Thread object, but you **haven't called its start()** method yet.
```java
Thread myThread = new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("Thread is running");
    }
});
// myThread is now in the NEW state
```
2. **RUNNABLE**: A thread enters the RUNNABLE state when its **start() method is invoked**. However, being in the RUNNABLE state doesn't necessarily mean the thread is currently executing. It signifies that the **thread is eligible to run** and is waiting for its turn to be scheduled by the operating system's thread scheduler. This state actually encompasses two sub-states:
   - READY: The thread is waiting to be assigned a CPU core by the scheduler.
   - RUNNING: The thread is currently executing on a CPU core.
The transition from NEW to RUNNABLE happens when you call thread.start(). The transition between READY and RUNNING is managed by the operating system's scheduler.
```java
myThread.start(); // myThread transitions to the RUNNABLE state
```
3. **BLOCKED**: A thread enters the BLOCKED state when it is waiting to acquire a monitor lock to enter a synchronized block or method, or after re-entering a monitor after being in the waiting state. Essentially, it's blocked waiting for another thread to release a lock.
```java
public class SharedResource {
    public synchronized void access() {
        // ... critical section ...
    }
}

SharedResource resource = new SharedResource();
Thread thread1 = new Thread(() -> resource.access());
Thread thread2 = new Thread(() -> resource.access());
thread1.start();
thread2.start(); // If thread1 acquires the lock first, thread2 will likely enter the BLOCKED state
```
4. **WAITING**: A thread enters the WAITING state when it calls one of the following methods:
   - **Object.wait()**: The thread waits indefinitely until another thread calls notify() or notifyAll() on the same object.
   - **Thread.join() (with no timeout)**: The current thread waits indefinitely until the specified thread terminates.
   - **LockSupport.park()**: The thread waits indefinitely unless a permit becomes available or it is unparked.
```java
Object lock = new Object();

Thread threadA = new Thread(() -> {
    synchronized (lock) {
        try {
            lock.wait(); // threadA enters the WAITING state
            System.out.println("Thread A woke up");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
});
threadA.start();

Thread threadB = new Thread(() -> {
    synchronized (lock) {
        lock.notify(); // Wakes up a single waiting thread (threadA)
    }
});
threadB.start();
```
5. **TIMED_WAITING**: A thread enters the TIMED_WAITING state when it calls one of the following methods with a specified timeout period:
   - **Thread.sleep(long millis)**: The thread sleeps for the specified duration.
   - **Object.wait(long timeout)**: The thread waits for the specified duration or until notified.
   - **Thread.join(long millis)**: The current thread waits for the specified duration or until the specified thread terminates.
   - **LockSupport.parkNanos(long nanos) or LockSupport.parkUntil(long deadline)**: The thread waits for a specified duration or until a deadline.
   - **Condition.await(long time, TimeUnit unit)**: The thread waits on a condition for a specified duration.
```java
Thread timedWaitingThread = new Thread(() -> {
    try {
        Thread.sleep(5000); // timedWaitingThread enters TIMED_WAITING for 5 seconds
        System.out.println("Timed waiting thread woke up");
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
});
timedWaitingThread.start();
```
6. **TERMINATED**: A thread enters the **TERMINATED* state when it has **completed its execution**. The run() method has finished, either normally or by throwing an uncaught exception. Once a thread has terminated, it cannot be restarted.
```java
Thread finishedThread = new Thread(() -> {
    System.out.println("Thread is finishing");
});
finishedThread.start();
// Once the "Thread is finishing" message is printed, finishedThread will enter the TERMINATED state.
```
***********************************************************************************************
## Q: When a thread is terminated can I restart it?
Ans: No, **once a thread in Java has reached the TERMINATED state, it cannot be restarted**.   

Think of a thread's lifecycle as a one-way journey. Once the run() method has completed its execution (either normally or by throwing an uncaught exception), the thread is considered dead. The resources allocated to that specific thread are reclaimed by the system.

If you need to execute the same task again, you have to create a new Thread object and call its start() method. Each call to new Thread(...) creates a fresh, independent thread of execution.
Attempting to call the **start() method on a terminated thread** will result in an **IllegalThreadStateException**.
```java
public class RestartThread {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            System.out.println("Running the task");
        };

        Thread thread1 = new Thread(task);
        thread1.start();
        thread1.join(); // Wait for thread1 to finish

        System.out.println("Thread 1 has terminated.");

        try {
            thread1.start(); // This will throw an IllegalThreadStateException
        } catch (IllegalThreadStateException e) {
            System.err.println("Error: Cannot restart a terminated thread.");
            System.err.println(e.getMessage());
        }

        // To run the task again, create a new Thread
        Thread thread2 = new Thread(task);
        thread2.start();
        System.out.println("Thread 2 has started.");
    }
}
```
***********************************************************************************
## Q: What is the difference between throws and throw?
- throw:
  1. Action: throw is a statement used to explicitly raise (or "throw") an exception within a method.
  2. Usage: It's followed by an instance of an exception class (or a subclass of Throwable).
  3. Purpose: It's how you signal that an exceptional condition has occurred within the current block of code and that the normal flow of execution cannot continue at that point.
  4. Location: throw is used inside the body of a method (or a static initializer or instance initializer).
```java
public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorServiceExample.processData(-1);
    }
    public static void processData(int value) {
    if (value < 0) {
        // Explicitly create and throw an exception
        throw new IllegalArgumentException("Input value cannot be negative: " + value);
    }
    // ... normal processing ...
}
}
```
#### Output:
```
ERROR!
Exception in thread "main" java.lang.IllegalArgumentException: Input value cannot be negative: -1
	at ExecutorServiceExample.processData(Main.java:11)
	at ExecutorServiceExample.main(Main.java:6)
```
- throws:
  1. Declaration: throws is a keyword used in the method signature (after the parameter list and before the method body).
  2. Usage: It's followed by a comma-separated list of exception classes.
  3. Purpose: It declares that the method might throw one or more checked exceptions during its execution. This informs the calling code that it needs to handle these potential exceptions (either by using a try-catch block or by declaring that it also throws those exceptions).
  4. Location: throws is used in the method declaration.
```java
import java.io.IOException;
import java.sql.SQLException;

public void readFileFromDatabase(String filename, String query) throws IOException, SQLException {
    // Code that might throw IOException (e.g., file operations)
    // Code that might throw SQLException (e.g., database operations)
}
```
#### In essence:
- throw is about doing the action of throwing an exception.
- throws is about making a promise (in the method signature) that the method might throw certain checked exceptions.
************************************************************************************************************************************
## Q: What are the different types of exceptions
Ans: In Java, exceptions are broadly categorized into two main types based on when the exception is checked by the compiler: Checked Exceptions and Unchecked Exceptions. There's also a third category, Errors, which are technically a subclass of Throwable but are generally considered distinct from exceptions in terms of how they should be handled.
1. Checked Exceptions (Compile-time Exceptions):
   - These are exceptions that the compiler forces you to handle (either by catching them using a try-catch block or by declaring that your method throws them).
   - They typically represent exceptional conditions that are reasonably predictable and recoverable, often related to external factors.
   - All exceptions that are subclasses of java.lang.Exception, excluding java.lang.RuntimeException and its subclasses, are checked exceptions.
- Examples:
  - IOException: Signals that an I/O operation has failed (e.g., reading or writing to a file).
  - SQLException: Indicates a problem accessing or processing data in a database.
  - ClassNotFoundException: Occurs when the Java Virtual Machine tries to load a class but cannot find its definition.
  - InterruptedException: Thrown when a thread is waiting, sleeping, or otherwise occupied, and is interrupted.
  - FileNotFoundException: A subclass of IOException indicating that a file could not be found.
2. Unchecked Exceptions (Runtime Exceptions):
  - These are exceptions that the compiler does not force you to handle.
  - They typically represent programming errors or unexpected conditions that are generally considered to be the fault of the programmer.
  - All subclasses of java.lang.RuntimeException are unchecked exceptions.
- Examples:
  - NullPointerException: Occurs when you try to access a member of a null object.
  - ArrayIndexOutOfBoundsException: Thrown when you try to access an array element with an index that is outside the bounds of the array.
  - IllegalArgumentException: Indicates that a method has been passed an illegal or inappropriate argument.
  - ArithmeticException: Occurs during erroneous arithmetic operations (e.g., division by zero).
  - ClassCastException: Thrown when you try to cast an object to a class of which it is not an instance.
  - NoSuchElementException: Typically thrown by iterators or scanners when there are no more elements.
**********************************************************************************************************
## Q: What do you mean by try with resource
"try-with-resources" is a fantastic feature introduced in Java 7 that significantly simplifies resource management, especially when dealing with resources that need to be closed after use, like file streams, database connections, and network sockets.
In essence, "**try-with-resources**" is a try statement that declares one or more resources. The key benefit is that the Java runtime automatically closes these resources after the try block (and any associated catch or finally blocks) completes, even if exceptions occur.
- **Automatic Resource Management**: The primary goal is to eliminate the boilerplate code typically found in finally blocks that are used to ensure resources are closed. Before try-with-resources, you'd often see something like this:
```java
InputStream inputStream = null;
try {
    inputStream = new FileInputStream("file.txt");
    // ... use the inputStream ...
} catch (IOException e) {
    // ... handle exception ...
} finally {
    if (inputStream != null) {
        try {
            inputStream.close();
        } catch (IOException e) {
            // ... handle close exception (often ignored) ...
        }
    }
}
```
- **Simplified Syntax**: With try-with-resources, the code becomes much cleaner and more readable:
```java
try (InputStream inputStream = new FileInputStream("file.txt")) {
    // ... use the inputStream ...
} catch (IOException e) {
    // ... handle exception ...
}
// inputStream is automatically closed here
```
**try-with-resources is a powerful and recommended construct in Java for automatically managing resources that need to be closed. It makes your code cleaner, more robust, and less prone to resource leaks. When you encounter a class that implements AutoCloseable, using it within a try-with-resources block is generally the best practice.**
******************************************************************************************************************
## Q: What is the difference between == and .equals() method?















