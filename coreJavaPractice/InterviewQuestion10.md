# Interview Question about Java 8 features
## Q: What are functional interfaces? Give examples.
A functional interface in Java is an interface that contains exactly one abstract method. It can have any number of static or default methods. The key characteristic is the single abstract method, which defines the contract of the interface.
### Why Functional Interfaces?
The introduction of functional interfaces in Java 8 was primarily to support "lambda expressions". Lambda expressions provide a way to define "anonymous functions" (functions without a name) directly in your code. These lambda expressions can then be assigned to variables of "functional interface types".
Think of a functional interface as defining the "shape" of a function. A lambda expression provides a concrete instance of that shape.
### The @FunctionalInterface Annotation:
While not strictly required, it's good practice to annotate your functional interfaces with "@FunctionalInterface". This annotation serves two purposes:
- Compiler Check: It instructs the compiler to ensure that the interface indeed has only "one abstract method". If you accidentally add another abstract method, the compiler will give you an error.
- Documentation: It clearly indicates the intent of the interface as a functional interface, making your code easier to understand.

### Examples of Built-in Functional Interfaces in Java:
Java provides a rich set of "built-in functional interfaces" in the "java.util.function" package. These cover many common use cases, reducing the need to define your own in many situations. Let's look at some popular ones:
1. Runnable:
   -
   - Abstract Method: void run()
   - Purpose: Represents a task that can be "executed by a thread". It takes no arguments and returns no result.
     ```java
      Runnable task = () -> System.out.println("Running a task!");
      new Thread(task).start();
     ```
2. Callable<V>:
   -
   - Abstract Method: V "call()" throws Exception
   - Purpose: Represents a task that returns a result of type V and can throw a checked exception. Used with ExecutorService to get results from asynchronous tasks.
     ```java
           import java.util.concurrent.Callable;
          import java.util.concurrent.ExecutionException;
          import java.util.concurrent.ExecutorService; 1 
          import java.util.concurrent.Executors;
          import java.util.concurrent.Future;

           Callable<String> taskWithResult = () -> "Result from Callable";
          ExecutorService executor = Executors.newSingleThreadExecutor();
          Future<String> future = executor.submit(taskWithResult);
          try {
              String result = future.get();
              System.out.println("Result: " + result);
          } catch (InterruptedException | ExecutionException e) {
              e.printStackTrace();
          } finally {
              executor.shutdown();
          }
     ```
3. Consumer<T>:
   -
   - Abstract Method: void accept(T t)
   - Purpose: Represents an operation that accepts a single input argument of type T and returns no result (performs a side effect). Often used in stream operations like forEach.
     ```java
      import java.util.List;
      import java.util.Arrays;
      import java.util.function.Consumer;
      
      List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
      Consumer<String> printName = name -> System.out.println("Hello, " + name + "!");
      names.forEach(printName);
     ```
4. Function<T, R>:
   -
   - Abstract Method: R apply(T t)
   - Purpose: Represents a function that accepts one argument of type T and produces a result of type R. Used in stream operations like map.
     ```java
      import java.util.List;
      import java.util.Arrays;
      import java.util.function.Function;
      
      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
      Function<Integer, Integer> square = n -> n * n;
      numbers.stream()
             .map(square)
             .forEach(result -> System.out.print(result + " ")); // Output: 1 4 9 16 25
      System.out.println();
     ```
5. Supplier<T>:
   -
   - Abstract Method: T get()
   - Purpose: Represents a supplier of results. It takes no arguments and returns a result of type T. Often used for lazy evaluation or generating values.
     ```java
      import java.util.function.Supplier;

      Supplier<Double> randomValueSupplier = () -> Math.random();
      System.out.println("Random value: " + randomValueSupplier.get());
     ```
6. Predicate<T>:
   -
   - Abstract Method: boolean test(T t)
   - Purpose: Represents a predicate (a boolean-valued function) of one argument of type T. Used in stream operations like filter.
     ```java
      import java.util.List;
      import java.util.Arrays;
      import java.util.function.Predicate;
      
      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
      Predicate<Integer> isEven = n -> n % 2 == 0;
      numbers.stream()
             .filter(isEven)
             .forEach(result -> System.out.print(result + " ")); // Output: 2 4 6
      System.out.println();
     ```
### Creating Your Own Functional Interfaces:
```java
@FunctionalInterface
interface StringProcessor {
    String process(String input);
}

public class CustomFunctionalInterfaceExample {
    public static void main(String[] args) {
        StringProcessor toUpperCase = text -> text.toUpperCase();
        StringProcessor addGreeting = text -> "Hello, " + text + "!";

        String message = "world";
        String upperCaseMessage = toUpperCase.process(message);
        String greetedMessage = addGreeting.process(message);

        System.out.println("Uppercase: " + upperCaseMessage); // Output: WORLD
        System.out.println("Greeted: " + greetedMessage);   // Output: Hello, world!
    }
}
```
   




