# What is volatile ?
volatile ensures that updates to a variable are always visible to all threads immediately,
preventing caching issues in a multithreaded environment.
## 🎯 Definition:
- volatile is a keyword in Java.
- It is used to mark a variable as "stored in main memory".
- Every read or write of a volatile variable is directly done on main memory,
not on the thread’s local cache.
## ✅ Use Case Example: 
You have multiple threads reading and writing a shared variable, and you want to ensure visibility across all threads.
```java
class Example {
    private volatile boolean flag = false;

    public void setFlagTrue() {
        flag = true;
    }

    public boolean isFlag() {
        return flag;
    }
}
```
"**Without volatile, a thread might not immediately see the updated value of flag.
With volatile, all threads immediately see the updated value.**"
********************************
*********************************
# What is functional interface and can we write functional interface without abstract method?
## ✅ Functional Interface in Java
### 🎯 Definition:
- A functional interface is an interface that contains exactly one abstract method.
- It can have multiple default and static methods, but only one abstract method.
### ✅ Use Case Example: 
Functional interfaces are mainly used to enable Lambda Expressions and Method References in Java 8+.
```java
@FunctionalInterface
interface MyFunction {
    void execute();
}
```
#### Lambda Expression:
```java
MyFunction func = () -> System.out.println("Executing function!");
func.execute();
````
### ✅ Popular Examples of Built-in Functional Interfaces:

|Interface | Abstract Method|
|----------|----------------|
|Runnable | void run()|
|Callable<T> | T call()|
|Comparator<T> | int compare(T o1, T o2)|
|Supplier<T> | T get()|
|Consumer<T> | void accept(T t)|
|Function<T,R> | R apply(T t)|
## ✅ Can we write a functional interface without an abstract method?
🚫 NO!
- A functional interface must have exactly one abstract method.
- If there is zero abstract methods, it’s NOT a functional interface.
- Also, if it has more than one abstract method, compiler will throw an error if @FunctionalInterface is used.
```java
@FunctionalInterface
interface InvalidFunction {
    void method1();
    void method2(); // ❌ Compiler Error: Invalid '@FunctionalInterface' annotation
}

```
```java
@FunctionalInterface
interface CorrectFunction {
    void method1(); // ✅ One abstract method

    default void helper() {
        System.out.println("Helper method");
    }

    static void util() {
        System.out.println("Utility method");
    }
}

```


