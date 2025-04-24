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
## Q: How do Streams work in Java 8?
Ah, Java 8 Streams! Think of them as a revolutionary way to process collections of data in a declarative and efficient manner. Instead of telling the computer how to process the data (using loops and manual iteration), you tell it what you want to achieve.
### The Core Idea: A Pipeline of Operations
Imagine a well-organized assembly line in a factory. Raw materials (your collection data) enter one end, go through a series of processing stations (stream operations), and emerge at the other end as a finished product (the result of your stream pipeline).
A Java 8 Stream is a sequence of elements that supports various aggregate operations. These operations are chained together to form a "stream pipeline".
### Key Characteristics of Streams:
1. Sequence of Elements: A stream represents a sequence of elements from a source (like a collection, array, or I/O channel).
2. Supports Aggregate Operations: Streams provide a rich set of built-in operations to perform on these elements, such as:
   - Filtering: Selecting elements based on a condition (like picking out only ripe mangoes from a basket).
   - Mapping: Transforming each element into a new form (like peeling each mango).
   - Sorting: Arranging elements in a specific order (like sorting mangoes by size).
   - Reduction: Combining elements to produce a single result (like making mango juice from all the peeled mangoes).
   - Collection: Gathering the processed elements into a new collection (like putting the sorted mangoes into a new tray).
3. Pipeline: Most stream operations return a new stream, allowing you to chain them together to form a pipeline. The data flows through this pipeline, with each operation transforming it in some way.
4. Laziness: Intermediate operations (like filter and map) are lazy. They are not executed until a terminal operation is invoked on the stream. This allows for efficient processing, as operations are only performed on the elements that are actually needed to produce the final result. Think of it like only peeling the mangoes you're actually going to use for the juice.
5. Processing in Batches (Internal Optimization): While you define operations on each element conceptually, the underlying implementation often processes data in chunks or batches for better performance, especially with parallel streams.
6. Traversal Only Once: The elements of a stream are typically consumed only once during the pipeline execution. Once a terminal operation is performed, the stream is considered consumed and cannot be reused. You'll need to create a new stream from the source again.
### The Stream Pipeline: Source, Intermediate Operations, Terminal Operation
A stream pipeline consists of three parts:
1. Source: This is where the stream originates. It could be a Collection (using stream() or parallelStream()), an Array (Arrays.stream()), a Supplier, or an I/O channel.
   ```java
      List<String> cities = Arrays.asList("Mumbai", "Delhi", "Kolkata", "Chennai", "Bangalore");
      Stream<String> cityStream = cities.stream(); // Sequential stream
      Stream<String> parallelCityStream = cities.parallelStream(); // Parallel stream
      
      int[] numbers = {1, 2, 3, 4, 5};
      IntStream numberStream = Arrays.stream(numbers);
   ```
2. Intermediate Operations: These operations transform or filter the stream. They always return a new stream, allowing for chaining. Examples include filter(), map(), flatMap(), sorted(), distinct(), peek().
   ```java
      Stream<String> filteredCities = cityStream.filter(city -> city.startsWith("B")); // Filters cities starting with "B"
      Stream<String> upperCaseCities = filteredCities.map(String::toUpperCase); // Converts to uppercase
   ```
3. Terminal Operation: This operation consumes the stream and produces a final result or a side effect. Once a terminal operation is called, the stream pipeline is executed. Examples include forEach(), collect(), reduce(), count(), min(), max(), anyMatch(), allMatch(), findFirst().
   ```java
      upperCaseCities.forEach(System.out::println); // Prints each uppercase city
      long count = cities.stream().filter(city -> city.length() > 5).count(); // Counts cities with length > 5
      List<String> collectedCities = cities.stream().filter(city -> city.contains("i")).collect(Collectors.toList()); // Collects cities containing "i" into a List
   ```
### How Laziness Works:
```java
List<String> fruits = Arrays.asList("apple", "banana", "orange", "grape", "kiwi");

fruits.stream()
      .filter(s -> {
          System.out.println("Filtering: " + s);
          return s.startsWith("a");
      })
      .map(s -> {
          System.out.println("Mapping: " + s);
          return s.toUpperCase();
      })
      .findFirst()
      .ifPresent(result -> System.out.println("First matching fruit (uppercase): " + result));
```
#### Output:
```
Filtering: apple
Mapping: apple
First matching fruit (uppercase): APPLE
```
Notice that the filter and map operations were only executed for "apple". As soon as findFirst() found the first matching element, the stream processing stopped. This is the benefit of laziness – operations are only performed as much as needed to get the terminal result.
### Sequential vs. Parallel Streams:
- Sequential Stream: Operations are performed on one element at a time, in the order they appear in the source (or as determined by intermediate operations). This is like a single worker processing items on the assembly line.
- Parallel Stream: The stream is divided into multiple substreams, and operations are performed on these substreams in parallel across multiple threads (using the Fork-Join framework). This can significantly speed up processing for large datasets and computationally intensive operations, like having multiple workers on the assembly line working simultaneously on different parts. You can get a parallel stream using parallelStream() on a collection or by calling .parallel() on an existing stream.
```java
long countParallel = numbers.parallelStream().filter(n -> n % 2 == 0).count();
```




