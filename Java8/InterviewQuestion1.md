# Features of Java 8
1. Lambda Expressions:
   -
   - Introduced functional-style programming.
   - You can pass behavior (methods) as data.
     ```java
      (a, b) -> a + b
     ```
2. Functional Interfaces:
   -
   - An interface with only one abstract method.
   - Example: Runnable, Callable, Comparator
   - New annotation: @FunctionalInterface
3. Streams API
   -
   - A powerful way to process collections (like filtering, mapping, sorting) in a functional style.
     ```java
        list.stream().filter(x -> x > 5).collect(Collectors.toList());
     ```
4. Default Methods in Interfaces
   -
   - Interfaces can now have method implementations using default keyword.
   - Helps in interface evolution without breaking existing code.
5. Optional Class
   -
   - Helps avoid NullPointerException.
   - Represents a value that may or may not exist.
     ```java
      Optional<String> name = Optional.of("John");
     ```
6. New Date and Time API (java.time)
   -
   - Introduced a brand new, immutable, ISO-standard date/time API.
   - Example classes:
     - LocalDate
     - LocalTime
     - LocalDateTime
     - Period
     - Duration
7. Method References
   -
   - Shortcut to call methods directly using ::.
     ```java
      list.forEach(System.out::println);
     ```
8. Collectors
   -
   - Part of Streams API.
   - Used to gather (collect) processed stream data into collections like Lists, Sets, or Maps.
9. Nashorn JavaScript Engine
   -
   - A new JavaScript engine to run JavaScript code from Java applications.
10. Parallel Streams
    -
    - Streams can be processed in parallel automatically to improve performance:
      ```java
        list.parallelStream().forEach(System.out::println);
      ``` 
***************************************************************************************************



  
