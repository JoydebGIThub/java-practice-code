# Interview Question
## Q: What is the purpose of Optional in Java 8?
Optional in Java 8! Think of it as a well-designed envelope that might or might not contain a value. Its primary purpose is to address the notorious problem of NullPointerExceptions in a more robust and expressive way.
### The Problem with null:
Before Java 8, the representation of a "missing" or "absent" value was often done using null. While seemingly simple, null has several drawbacks:
- NullPointerException (NPE): The most common issue. Trying to access a method or field of a null reference leads to a runtime NullPointerException, which can crash your application.
- Lack of Explicit Intent: When you see a variable that can be null, it's not immediately clear from the type system whether it's intended to be nullable or if it's a potential error. Developers have to rely on documentation or guesswork.
- Boilerplate Null Checks: To avoid NPEs, developers often have to write numerous if (object != null) checks, making the code verbose and less readable.
- Error-Prone: It's easy to forget these null checks, leading to unexpected runtime exceptions.
### The Solution: Optional - A Container for Maybe-Present Values
Optional<T> is a container object that may or may not contain a non-null value of type T. It provides a type-level solution to represent the absence of a value. Instead of a variable potentially holding null, it holds an Optional object which explicitly indicates whether a value is present or absent.
### Purpose of Optional:
1. Explicitly Represent Absence: Optional forces you to think about the case where a value might not be present. By using Optional, the return type of a method clearly signals that the result might be absent, making it more obvious to the caller.
2. Reduce NullPointerExceptions: By wrapping potentially null values in Optional objects, you shift the responsibility of handling the absence of a value from implicit null checks to explicit operations provided by the Optional class. This encourages a more controlled and less error-prone way of dealing with missing values.
3. Improved Code Readability: Code that uses Optional tends to be more expressive and easier to understand. The methods provided by Optional (like isPresent(), orElse(), orElseGet(), map(), flatMap(), ifPresent()) clearly convey the intent of handling potential absence.
4. Functional Style: Optional is designed to work well with Java 8 streams and lambda expressions, promoting a more functional style of programming by allowing you to chain operations on potentially absent values without explicit null checks.
### How to Use Optional:
- Optional.empty(): Creates an empty Optional instance (representing the absence of a value).
  ```java
    Optional<String> emptyOptional = Optional.empty();
  ---
- Optional.of(value): Creates an Optional instance containing the given non-null value. If value is null, it immediately throws a NullPointerException. Use this when you are certain the value should not be null.
  ```java
    String nonNullValue = "Hello";
    Optional<String> optionalWithValue = Optional.of(nonNullValue);
  ```
- Optional.ofNullable(value): Creates an Optional instance containing the given value if it is non-null. If value is null, it creates an empty Optional. This is the most common way to create Optional from a potentially null source.
  ```java
    String potentiallyNullValue = null;
    Optional<String> optionalWithNullable = Optional.ofNullable(potentiallyNullValue);
  ````
#### Common Optional Methods:
- isPresent(): Returns true if a value is present in the Optional, false otherwise.
  ```java
    Optional<String> optional = Optional.ofNullable("World");
    if (optional.isPresent()) {
        System.out.println("Value is present: " + optional.get());
    }
  ```
- get(): Returns the value if it is present. Use with caution! Calling get() on an empty Optional will throw a NoSuchElementException. Always check isPresent() first or use safer alternatives.
  ```java
    Optional<String> optional = Optional.ofNullable("Java");
    if (optional.isPresent()) {
        String value = optional.get();
        System.out.println(value);
    }
  ```
- orElse(other): Returns the value if present, otherwise returns the other value provided as an argument.
  ```java
    Optional<String> optional = Optional.ofNullable(null);
    String value = optional.orElse("Default Value");
    System.out.println(value); // Output: Default Value
  ```
- orElseGet(supplier): Returns the value if present, otherwise returns the result of invoking the Supplier function provided as an argument. Use this when the default value is expensive to compute and should only be created if the Optional is empty.
  ```java
    Optional<String> optional = Optional.ofNullable(null);
    String value = optional.orElseGet(() -> System.getProperty("user.home"));
    System.out.println(value); // Output will be the user's home directory
  ```
- orElseThrow(exceptionSupplier): Returns the value if present, otherwise throws an exception created by the Supplier function.
  ```java
    Optional<String> optional = Optional.ofNullable(null);
    String value = optional.orElseThrow(() -> new IllegalArgumentException("Value is missing!"));
    // This will throw an IllegalArgumentException
  ```
- ifPresent(consumer): Executes the Consumer function with the value if it is present. Does nothing if the Optional is empty.
  ```java
    Optional<String> optional = Optional.ofNullable("Action");
    optional.ifPresent(action -> System.out.println("Performing: " + action));
  ```
- map(function): If a value is present, applies the Function to it and returns an Optional containing the result. If the Optional is empty, it returns an empty Optional. This allows you to chain transformations without explicit null checks.
  ```java
    Optional<String> optional = Optional.ofNullable("example");
    Optional<Integer> lengthOptional = optional.map(String::length); // Optional containing 7
    Optional<String> emptyOptional = Optional.empty();
    Optional<Integer> emptyLengthOptional = emptyOptional.map(String::length); // Empty Optional
  ```
- flatMap(function): Similar to map, but the Function you provide here should return an Optional. flatMap flattens the result, so if the original Optional has a value, and the function returns Optional<U>, flatMap returns Optional<U> directly. This is useful when dealing with nested Optionals.
  ```java
    Optional<User> userOptional = findUserById(123); // Returns Optional<User>
    Optional<String> emailOptional = userOptional.flatMap(User::getEmail); // User::getEmail returns Optional<String>
  ```



  
