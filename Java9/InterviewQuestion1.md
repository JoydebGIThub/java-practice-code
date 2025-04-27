# ✅ Why and in which version private methods were introduced in Java interface?
Private methods were introduced in interfaces in Java 9 to allow code reusability inside default and static methods and to keep the interface code cleaner and DRY (Don't Repeat Yourself)
## 🎯 In which version?
- Java 9 introduced private methods inside interfaces.
## 🎯 Why were private methods added to interfaces?
- To improve code reusability and reduce duplication inside interfaces.
- When you have multiple default methods or static methods in an interface, and they share common code, you can move that common code into a private method.
- This keeps the interface code clean, modular, and less repetitive.
## ⚡ Before Java 9 (Java 8):
- Interfaces allowed default and static methods (with body).
- But no private helper methods, so developers had to copy-paste repeated logic inside each default method.
## ⚡ After Java 9:
- Interfaces can have:
  - "private" methods.
  - "private static" methods.
### 🛑 Important:
- Private methods are "NOT inherited" by implementing classes.
- They are only for internal use inside the interface.
## 🔥 Example Code:
```java
interface Vehicle {

    default void start() {
        showStartingMessage();
        System.out.println("Vehicle is starting");
    }

    default void stop() {
        showStoppingMessage();
        System.out.println("Vehicle is stopping");
    }

    private void showStartingMessage() {
        System.out.println("Preparing to start...");
    }

    private void showStoppingMessage() {
        System.out.println("Preparing to stop...");
    }
}

```
***********************************************************************


