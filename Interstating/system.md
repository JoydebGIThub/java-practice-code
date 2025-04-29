## What is System in Java?
- System is a final class in java.lang package.
- It cannot be instantiated (you can't create an object of it).
- It contains static fields and static methods that are useful for interacting with the environment in which the Java program is running.
- It's automatically available in every Java program because java.lang.* is imported by default.
### ✅ Main Components inside System Class:
- System.out → Standard output stream (usually the console)
- System.in → Standard input stream (usually the keyboard)
- System.err → Standard error stream (for printing error messages)
- Utility methods like:
  - System.currentTimeMillis()
  - System.nanoTime()
  - System.exit()
  - System.gc()
  - System.getenv()
  - System.getProperties()
#### Example:
1. Printing Output (System.out)
```java
public class SystemOutExample {
    public static void main(String[] args) {
        System.out.println("Hello, World!");   // println prints with a new line
        System.out.print("Welcome to Java!");  // print does not add new line
        System.out.printf("Formatted number: %.2f", 45.6789); // formatted output
    }
}

```
"**System.out is a PrintStream object.**"
2. Reading Input (System.in) (basic way)
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
       Scanner sc= new Scanner(System.in);
      System.out.print("Enter the value: ");
      int a= sc.nextInt();
      sc.nextLine();
      System.out.println("The value is: "+ a);
       System.out.print("Enter the name: ");
       String j = sc.nextLine();
       System.out.println("The name is: "+ j);
      sc.close();
    }
}
```
"**System.in is an InputStream object (reads bytes; Scanner wraps it to make it easier).**"
3. Error Output (System.err)
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
     try{
        int result= 10/0;
     }catch(ArithmeticException e){
         System.err.println("Error: Divided by Zero");
     }
     finally{
         System.out.println("Hello");
     }
    }
}
```
"**System.err is also a PrintStream, just like System.out, but intended for errors.**"
4. Getting Current Time (currentTimeMillis and nanoTime)
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
     long startTime = System.currentTimeMillis();
     long nanoTime = System.nanoTime();
     System.out.println("Current start time: "+startTime);
     System.out.println("nanoTime: "+nanoTime);
     for (int i = 0; i < 1000000; i++) {
            Math.sqrt(i);
        }
    long endTime = System.currentTimeMillis();
     long endnanoTime = System.nanoTime();
     System.out.println("The time taken by currentTime "+(endTime- startTime)+" milisec");
     System.out.println("The time taken by nanoSec "+(endnanoTime - nanoTime)+" nanosec");
    }
}
```
#### Output: 
```
Current start time: 1745916270464
nanoTime: 9723929911299
The time taken by currentTime 87 milisec
The time taken by nanoSec 86885290 nanosec
```
"**currentTimeMillis() → gives current time in milliseconds since Jan 1, 1970 (epoch).**"
"**nanoTime() → gives a more precise time value, useful for measuring elapsed time.**"
5. Exiting Program (System.exit)
```java
public class SystemExitExample {
    public static void main(String[] args) {
        System.out.println("This is before exit.");
        System.exit(0); // 0 indicates normal termination
        System.out.println("This will never be printed.");
    }
}
```
"**System.exit(status) terminates the program immediately.**"
```
status = 0 → normal,
status != 0 → abnormal.
```

6. Requesting Garbage Collection (System.gc)
```java
public class SystemGcExample {
    public static void main(String[] args) {
        String str = new String("Hello, Java");
        str = null; // Eligible for GC

        System.gc(); // Request GC to run (but no guarantee)

        System.out.println("GC requested");
    }
}

```
"**System.gc() suggests the JVM to perform garbage collection.
JVM may or may not immediately act on it.**"

7. Reading Environment Variables (System.getenv)
```java
public class SystemEnvExample {
    public static void main(String[] args) {
        String path = System.getenv("PATH");
        System.out.println("PATH environment variable: ");
        System.out.println(path); ///rbin
    }
}
```
"**System.getenv() fetches environment variables of the operating system.**"

8. Getting System Properties (System.getProperties)
```java
public class SystemPropertiesExample {
    public static void main(String[] args) {
        System.out.println("Java Version: " + System.getProperty("java.version")); //Java Version: 21.0.7
        System.out.println("OS Name: " + System.getProperty("os.name"));//OS Name: Linux
        System.out.println("User Home Directory: " + System.getProperty("user.home"));//User Home Directory: /home/ubuntu
    }
}
```
"**System.getProperties() gets properties like Java version, OS name, User directory, etc.**"



