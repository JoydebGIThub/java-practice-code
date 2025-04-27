# Scenario : Let assume in java we have 2 interfaces with same default methods and a class implements the both interfaces now if we want to print the value inside those default methods then how JVM will understand which default methods needs to be called? 
➡️ This is a classic Java 8 "multiple inheritance" problem with default methods.
## ✅ Problem:
- You have Interface A and Interface B, both have same default method (say default void show()).
- A Class C implements both interfaces.
## ❓ Question:
- Which show() method should JVM call when you create an object of C?
## 🎯 Answer:
- JVM will NOT decide automatically.
- We must "override the show()" method in your class C to resolve the conflict.
- Inside that overridden method, you can choose to call any specific interface's method using:
  ```java
    InterfaceName.super.methodName()
  ```
### 🔥 Example Code:
```java
interface A {
    default void show() {
        System.out.println("Show from A");
    }
}

interface B {
    default void show() {
        System.out.println("Show from B");
    }
}

class C implements A, B {
    @Override
    public void show() {
        // Manually resolve the conflict
        A.super.show();  // or B.super.show();
        B.super.show();  // You can even call both if you want
        System.out.println("Show from C");
    }
}

public class Test {
    public static void main(String[] args) {
        C obj = new C();
        obj.show();
    }
}

```
### Output:
```
Show from A
Show from B
Show from C
```
## 🛑 Important Rule:
If you don't override, you will get a compile-time error:
- "Duplicate default methods named show with the parameters () are inherited from the types A and B."


