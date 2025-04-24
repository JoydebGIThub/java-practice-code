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
***
***
## Q: What are default and static methods in interfaces?
### Default Methods: Adding Implementation to Interfaces
Before Java 8, interfaces could only declare abstract methods (methods without implementation). If you wanted to add a new method to an interface, all the classes that implemented that interface would be forced to provide an implementation for the new method, even if that implementation was the same across many classes or not needed at all. This posed a significant challenge for evolving interfaces without breaking existing implementations.

Default methods solve this problem by allowing you to provide a default implementation for a method directly within the interface. Classes that implement the interface can either use this default implementation or override it with their own specific implementation.

#### Purpose of Default Methods:
1. Interface Evolution: Allows you to add new methods to existing interfaces without breaking the compatibility of classes that already implement them. The implementing classes can choose to adopt the default implementation or provide their own.
2. Providing Common Functionality: Enables interfaces to provide common utility methods that implementing classes can reuse. This promotes code reuse and reduces redundancy.
#### Syntax of Default Methods:
```java
interface MyInterface {
    // Abstract method (must be implemented by implementing classes)
    void abstractMethod();

    // Default method (implementing classes can use or override)
    default void defaultMethod() {
        System.out.println("Default implementation of defaultMethod in MyInterface");
        // You can include method body here
    }
}

class MyClass implements MyInterface {
    @Override
    public void abstractMethod() {
        System.out.println("Implementation of abstractMethod in MyClass");
    }

    // Can choose to not override defaultMethod(), in which case the
    // default implementation from MyInterface will be used.
}

class AnotherClass implements MyInterface {
    @Override
    public void abstractMethod() {
        System.out.println("Implementation of abstractMethod in AnotherClass");
    }

    // Override the defaultMethod() with a custom implementation
    @Override
    public void defaultMethod() {
        System.out.println("Custom implementation of defaultMethod in AnotherClass");
    }
}

public class DefaultMethodExample {
    public static void main(String[] args) {
        MyClass obj1 = new MyClass();
        obj1.abstractMethod();
        obj1.defaultMethod(); // Uses the default implementation

        AnotherClass obj2 = new AnotherClass();
        obj2.abstractMethod();
        obj2.defaultMethod(); // Uses the overridden implementation
    }
}
```
#### Output:
```
Implementation of abstractMethod in MyClass
Default implementation of defaultMethod in MyInterface
Implementation of abstractMethod in AnotherClass
Custom implementation of defaultMethod in AnotherClass
```
### Static Methods in Interfaces: Utility Functions Within the Interface
Before Java 8, if you wanted to have utility methods related to an interface, you would typically create a separate utility class (e.g., Collections for Collection). Java 8 allowed interfaces to have static methods, which are associated with the interface itself, not with any specific instance of a class that implements the interface.
#### Purpose of Static Methods in Interfaces:
1. Utility Functions Related to the Interface: Allows you to group utility methods directly within the interface where they logically belong. This improves organization and discoverability of related functions.
2. Factory Methods: Interfaces can provide static factory methods to create instances of classes that implement the interface
#### Syntax of Static Methods:
```java
interface MyUtilityInterface {
    // Abstract method
    String processText(String text);

    // Static utility method related to the interface
    static boolean isNotNullOrEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    // Static factory method
    static MyUtilityInterface createProcessor(boolean toUpperCase) {
        if (toUpperCase) {
            return String::toUpperCase; // Method reference
        } else {
            return String::toLowerCase; // Method reference
        }
    }
}

class MyTextProcessor implements MyUtilityInterface {
    @Override
    public String processText(String text) {
        return "Processed: " + text;
    }
}

public class StaticMethodInterfaceExample {
    public static void main(String[] args) {
        String input = "  Hello World  ";

        // Calling the static method of the interface directly
        boolean notEmpty = MyUtilityInterface.isNotNullOrEmpty(input);
        System.out.println("Is not null or empty: " + notEmpty);

        // Using the static factory method
        MyUtilityInterface upperCaseProcessor = MyUtilityInterface.createProcessor(true);
        System.out.println(upperCaseProcessor.processText(input));

        MyUtilityInterface lowerCaseProcessor = MyUtilityInterface.createProcessor(false);
        System.out.println(lowerCaseProcessor.processText(input));
    }
}

````
#### Output:
```
Is not null or empty: true
Processed:   HELLO WORLD
Processed:   hello world
```

