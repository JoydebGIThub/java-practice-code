# ✅ When to use Default Method vs Static Method in Interface?
## 🎯 Default Method
- Use default methods when you want to provide a method with a body that can be inherited and overridden by implementing classes.
- It is meant for "instance behavior".
- Important: Default methods belong to instance of class, not to the interface.
## 🎯 Static Method
- Use static methods when you want utility methods(You can use them again and again in different parts of your program without having to write the same code over and over) inside the interface that are NOT inherited by implementing classes.
- It is meant for helper behavior or utility functionality.
- tatic methods can be called only as InterfaceName.methodName(), not from instance.

"**Use a default method when you want instance methods that implementing classes can inherit and override.
Use a static method when you want utility/helper methods related to the interface that are not tied to any instance**"

| Feature                     | Default Method             | Static Method              |
|-----------------------------|----------------------------|----------------------------|
| Inherited by class?         | ✅ Yes                     | ❌ No                      |
| Can be overridden?          | ✅ Yes                     | ❌ No                      |
| Called using?               | Instance or ClassName.super.method() | InterfaceName.methodName() |
| Purpose                     | Provide default instance behavior | Utility/helper functionality |
************************************************************************************************************************************
# What is variable arguments?
## ✅ Variable Arguments (Varargs) in Java
Variable arguments (or varargs) in Java allow you to pass a variable number of arguments to a method. Instead of specifying a fixed number of parameters, you can use varargs to "**pass any number of arguments of the same type**". Varargs makes it easier to work with methods that can accept an arbitrary number of arguments.
### ✅ Use Case Example:
```java
public class VarargsExample {

    public static int sum(int... numbers) {
        int result = 0;
        for (int num : numbers) {
            result += num;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(sum(1, 2, 3));  // Output: 6
        System.out.println(sum(5, 10));    // Output: 15
        System.out.println(sum(7));        // Output: 7
        System.out.println(sum());         // Output: 0
    }
}

```
#### 🛑 Important Notes:
- Varargs must be the last parameter in the method signature if the method has multiple parameters.
- Varargs are treated as an array inside the method.
- You can combine varargs with other parameters, but varargs should always be the last one.
- A method can only have one varargs parameter.
```java
public class VarargsExample {

    public static void printMessages(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        printMessages("Hello", "World", "Java");  // Output: Hello, World, Java
    }
}

```
************************************
# Can we replace the String[] in main method with variable arguments?
Yes, you can replace the "String[] args" in the "main" method with variable arguments (varargs). In fact, the main method in Java already uses varargs to handle command-line arguments.
## The standard main method signature is:
```java
public static void main(String[] args)
```
"**This method signature uses varargs (String... args), which allows you to pass any number of String arguments when starting the Java application.**"
### Important Points:
- The String... args in the main method allows any number of arguments (including none).
- The behavior is the same as using String[] args because String... args is internally treated as a String[] array.
