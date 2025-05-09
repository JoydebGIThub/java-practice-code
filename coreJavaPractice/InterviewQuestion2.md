# Interview Therory Question:
## Q: What is the difference between == and .equals() in java
- **==:** Compares object "**references (memory address)**". For primitives, it compares the actual "**values**". Think of it asking "**Are these the exact same object in memory?**"
- **.equals()**: Compares the "**content or logical equality**" of the objects. The default behavior is the same as ==, but it's meant to be "**overridden**" by classes to define what "**equal content**" means for their instance. Think of it as asking "**Do these objects have the same meaningful data?**"

**Note: Use "==" for reference equality and ".equals()" for content equality. Remember to override ".equals() (and hashCode())" in your custom classes**


```java
// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class Main {
    public static void main(String[] args) {
        //Primitive types
        int num1= 5;
        int num2= 5;
        System.out.println("Primitive comparison (==): "+ (num1==num2)); //true
        
        //Object references (Strings- spacial case of objects)
        String str1="Hello";
        String str2="Hello";
        String str3= new String("Hello");
        System.out.println("String reference comparison (==): "+ (str1==str2)); // true : String constant pool same reference of the address
        System.out.println("String reference comparison (==): "+ (str1==str3)); // false new create new reference instance
        System.out.println("String reference comparison (.equals()): "+ (str1.equals(str2))); //true compare the value inside it
        System.out.println("String reference comparison (.equals()): "+ (str1.equals(str3))); // true
        
        //Custom objects
        Point p1= new Point(1, 2);
        Point p2= new Point(1, 2);
        Point p3= p1;
        System.out.println("Object reference comparison (==): "+ (p1==p2)); //false (different point object)
        System.out.println("Object reference comparison (==): "+ (p1==p3)); // true (same point object)
        System.out.println("Object reference comparison (.equals()): "+ (p1.equals(p2))); // true (same content because equals is overridden)
        
    }
    
}
class Point{
    int x;
    int y;
    
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public boolean equals(Object obj){
        if(this == obj) return true; //same object in memory
        if(obj == null || getClass() != obj.getClass()) return false; // null or different class
        Point other= (Point) obj;
        return x==other.x && y == other.y; // Compare the relevent fields(content)
    }
    
    public int hashCode(){
        return java.util.Objects.hash(x, y); //Important to override hashCode with equals
    }
}
```
***
## Q: Explain the memory model in Java. What are heap and stack memory?
- The **Java Memory Model** (JMM) isn't a **physical memory layout** but rather a specification that defines how threads in Java interact with the computer's memory. It governs things like:
  - Visibility: When changes made by one thread to shared variables are visible to other threads.
  - Ordering: The rules that determine the order in which operations in different threads can appear to execute.
  - Atomicity: Ensuring that operations appear to happen as a single, indivisible unit.

When a Java program runs, the JVM manages various memory areas. The two most frequenty discussed and essential ones are the "**Heap**" and the "**Stack**".
1. Heap Memory:
   - Purpose: The Heap is the runtime data area where "**objects (instance of classes)**" and their corresponding instance variables are allocated. It's also where arrays are stored.
   - Characteristics:
     - Shared: All threads in a Java application share the same Heap memory.
     - Dynamic Allocation: Memory for objects is allocated dynamically when the "new" keyword is used.
     - Garbage Collection: The Heap is managed by the JVM's garbage collector. Objects that are no longer referenced by any live part of the program become eligible for garbage colloction, and their memory is reclaimed.
     - Size: Th size of the Heap can be configured at JVM startup using flags like "-Xms (initial heap size)" and "-Xmx (maximum heap size)".
     - Organization (Generational GC): For efficient garbage collection, the Heap is often logically divided into generations (Young Generation, Old Generation, Permanent Generation/Metaspace - through the latter has evolved)
    - Analogy: Imagine a large shared warehouse where you can store various items(objects). Anyone working in the warehouse (threads) can access these items. When an item is no longer needed, the cleanup crew (garbage collector) comes and removes it to make space.
2. Stack Memory:
   - Purpose: Java Stack memory is used for "**method execution**" and storing "**local variables**", "**method parameters**" and the "**execution context (like the current line of code being executed)**". Each thread in a Java application has its own private JVM Stack.
   - Characteristics:
         - Thread-Local: Each thread has its own separate Stack. Data stored in one thread's stack is not accessible to other threads.
         - LIFO (Last-In, First-Out): Method calls and local variables are pushed into the stack when a method is entered, and they are popped off when the method completes.
         - Automatic Allocation and Deallocation: Memory for local variables and method frames is automatically allocated when a method is called and deallocated when the method returns.
         - Size: The size of each thread's Stack can be configured using the "-Xss" JVM flag.
         - StackOverflowError: If a thread's Stack exceeds its allocated size (Often due to deep or infinite recursion), a "StackOverflowError" is thrown.
    - Analogy: Think of a stack of plates where each plate represents a method call. When you call a method, a new plate is added to the top. Local variables and other method-specific information are kept on the plate. When the method finishes, the plate is removed. Each worker (thread) has their own stack of plates.
***
## Q: What is the difference between checked and unchecked exceptions?
### Checked Exception:
- Definition: Checked exception are exceptions that the compiler forces you to handle (either by catching them in a "try-catch" block or declaring them in the "throws" clause of the method signature).
- Inheritance: They are subclasses of the "Exception" class, excluding "RuntimeException".
- Compiled-Time Check: The compiler verifies if you code handles these exceptions. If you don't you'll get a compile-time error.
- Intent: They typically represent exceptional conditions that are reasonably predictable and recoverable, often related to external resources or environmental issues ("IOException" when dealing with files, "SQLException" when interaction with databases). The API designers expect you to anticipate and handle these situations gracefully.

### Unchecked Exceptions:
- Definition: Unchecked exceptions are exceptions that the compiler does not force you to handle. You can catch them if you want, but you're not required to.
- Inheritance: They are subclasses of the "RuntimeException" class (and Error).
- Runtime Check: These exceptions typically occur due to programming error or unexpected runtime conditions that are often dificult or impractical to anticipate and recover from in the general flow of the program.
- Intent: They usually indicate bugs in your code (e.g, NullPointerException, ArrayIndexOutOfBoundsException, IllegalArgumentException). The philosophy is that trying to handle every potential occurrence of these world lead to overly verbose and less readabke code. It's generally better to fix the underlying bug. "Error" subclasses usually represent serious JVM problems that applications typically shouldn't try to handle (OutOfMemoryError, StackOverflowError).

***
## Q: How does Java handle memory management and garbage collection?
- Java employs and "automatic memory management system", primarily through its "garbage collector". This significantly reduces burden on developers comared to languages like C/C++ where manual memory allocation and dealloaction are required.

### Memory Management in Java:
1. JVM's Role: The Java Virtual Machine is responsible for **allocating and deallocating memory** duing the execution of a Java program. It manages different memory areas, mostly notably the "Heap" and the "Stack":
   - Heap: This is where objects (instances of classes) and arrays are allocated. It's a shared memory area among all threads.
   - Stack: Each thread has its own private stack, used for storing local variables, method parameters, and the call stack. Memory allocation and deallocation on the stack are automatic and tied to the lifecycle of the method calls.
2. Automatic Allocation: When you create a new object using the "new" keyword, the JVM automatically allocates space for it on the Heap.
3. Garbage Collection (GC): The Core of Automatic Deallocation: The key to Java's automatic memory management is the garbage collector. It's a background process that identifies and reclaims memory occupied by objects that are no longer reachable by any live part of the application.

***
## Q: What are volatile, synchronized and transient keywords?
1. **volatile Keyword**:
   - Purpose: The "**volatile**" keyword is applied to "**instance variables**". It ensures that all threads see the most up-to-date value of the variable. It addresses the "**visibility problem**" in "multithreaded environments".
   - How it works:
     - When a variable is declared "volatile", the JVM ensures that **every read of the variable will be directly from the main memory**, and every write to the variable will be immediately fluched back to the main memory.
     - It **prevents threads from holding a local**, **cached copy** of the variable that might become stale.
   - Important Note: "**volatile**" only guarantees "**visibility**" (that changes are seen by other threads). It does not guarantee "**atomicity**" for compound operation (like incrementing "i++" which involves read, modify, write). **For atomic operations, you'd typically need "synchronized" or "java.util.concurrent" classes.**
   - Use Cases: Status flags, simple counters, or variables where immediate visibility of changes across threads in crucial.
```java
clas SharedResource{
    public volatile boolean running = true;
    public void stop(){
        running= false; //Changes to 'running' will be immediately visible to other threads.
    }

    public void workerThread(){
        while(running){
            //Perform some work
        }
        System.out.println("Worker thread stopped");
    }
}
```

2. **synchronized Keyword**:
   - Purpose: The "**synchronized**" keyword is used to control access to shared recources by multiple threads. It provides a mechanism for "mutual exclusion" and also ensures "**visibility**" of changes made within a synchronized block or method.
   - How to works:
     - When a thread enters a **"synchronized" method or a "synchronized" block**, it acquires a lock on the object (for instance methods or blocks) or the class object (for static methods or blocks).
     - Only one thread can hold the lock at a time. Other threads trying to enter the same synchronized section will be blocked until the lock is released.
     - When a thread exits a synchronized section, it releases the lock.
     - Synchronization also establishes a "**happens-before**" relationship, ensuring that any changes made within a synchronized block are visible to subsequent threads that acquire the same lock
    - Use Cases: Protecting critial sections of code where shared data is accessed and modified to prevent "**race conditions**" and ensure data integrity.

### Synchronized method
```java
class Counter{
    private int count=0;
    public synchronized void increment(){
        count++; //Only one thread can execute this method at a time.
    }

    public int getCount(){
        return count;
    }
}
```
### Synchronized block
```java
class SharedList{
    private java.util.List<String> list= new java.util.ArrayList<>();
    private final Object lock = new Object();

    public void addItem(String item){
        synchronized(lock){
            list.add(item);// Access to the list is synchronized on the 'lock' object.
        }
    }

    public String getItem(int index){
        synchronized(lock){
            return list.get(index);
        }
    }
}
```

3. **transient Keyword**:
- Purpose: The "**transient**" keyword is used to mark instance variables that should "**not be serialized**" when an object is written to an object stream (during serialization for saving to a file or sending over the network).
- How it works: When the default serialization mechanism attempts to save the state of an object, it will "**skip over**" any fields declared as "**transient**". These fields will not be included in the serialized data. When the object is deserialized, "**transient**" fields will be initialized to their default values (null for object references, 0 for integers, false for booleans).
- Use Case:
  - Storing sensitive information (like passwords or security keys) that shouldn't be persisted.
  - Caching values that can be recomputed or are not meaningful after deserialization.
  - References to non-serializable objects.
  - Optimizing serialization by excluding large or unnecessary data.

```java
import java.io.Serializable;
class User implements Serializable{
    private String username;
    private transient String password; // Password will not be serialized
    private int loginCount;
    private transient java.util.Date lastLogin; //Last login time will not be serialized

    public User(String username, String password, int loginCount, java.util.Date lastLogin){
        this.username=username;
        this.password=password;
        this.loginCount=loginCount;
        this.lastLogin=lastLogin;
    }
}
```
