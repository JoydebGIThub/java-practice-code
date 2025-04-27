# Scenario: Let assume we write a Test class with a static method. Then in main method, we write:
"**Test obj = null;
obj.staticMethod();
What will be happend?**"
In Java, when you call a static method using an instance (even if the instance is null), the JVM still calls the static method on the class itself, not on the object. This behavior is because static methods are associated with the class, not instances of the class.
```java
class Test {
    // A static method
    public static void staticMethod() {
        System.out.println("Static method called");
    }
}
```
```java
public class Main {
    public static void main(String[] args) {
        Test obj = null;      // obj is null
        obj.staticMethod();    // Calling static method on a null reference
    }
}

```
### What happens?
- When you call obj.staticMethod(), you're invoking the static method on the Test class, not on the obj reference.
- Even though obj is null, it does not cause a NullPointerException because the static method is not associated with the object (obj), but with the class (Test).
- The JVM will still look for the static method in the Test class and invoke it, regardless of whether the reference is null.
**************************************
# 🎯 What is the equals() and hashCode() Contract? 
The equals() and hashCode() methods in Java are closely related and must work together correctly, especially when objects are used in collections like HashMap, HashSet, Hashtable etc.
## The contract states:
- If two objects are equal according to the equals(Object o) method, then they must have the same hash code.
- If two objects have the same hash code, they may or may not be equal (collisions are possible).
- If two objects are not equal, they can have:
  - Different hash codes (recommended for better performance).
  - Same hash codes (allowed but will degrade performance in hash-based collections).
### 🛑 Important:
- Breaking this contract can lead to unexpected behavior when storing objects in hash-based collections.
- hashCode() is used to find the bucket location in the Hash table; equals() is used to check the actual equality.
```java
import java.util.Objects;

public class Employee {
    private int id;
    private String name;

    // Constructor
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Overriding equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id && Objects.equals(name, employee.name);
    }

    // Overriding hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

```
"**In Java, if two objects are considered equal using equals(), then they must return the same hashCode(). Breaking this contract can cause hash-based collections like HashMap or HashSet to behave incorrectly.**"

************************
# ✅ What happens if you override equals() but NOT hashCode()?
🎯 Problem: If you override only the equals() method but do not override hashCode(), your object breaks the equals-hashCode contract.
## Specifically:
- Two objects can be "equal" according to equals(),
- But they can have different hash codes (because the default hashCode() from Object class is still being used).
- As a result, in hash-based collections like HashMap, HashSet, Hashtable:
  - Even though two objects are "equal", they will be placed in different buckets.
  - This can cause unexpected behavior like duplicate entries or failure to retrieve objects.
```java
import java.util.HashSet;

class Employee {
    int id;
    String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Only equals() overridden
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee emp = (Employee) o;
        return this.id == emp.id && this.name.equals(emp.name);
    }

    // hashCode() NOT overridden
}

public class TestExample {
    public static void main(String[] args) {
        HashSet<Employee> set = new HashSet<>();
        Employee e1 = new Employee(1, "John");
        Employee e2 = new Employee(1, "John");

        set.add(e1);
        set.add(e2);  // Should not add because logically e1 == e2

        System.out.println(set.size()); // OUTPUT will be 2 😵 (Expected 1)
    }
}

```
#### ✅ Expected behavior:
Since e1.equals(e2) returns true, both should be treated as duplicates and only one should be stored.
#### ✅ Actual behavior without overriding hashCode():
- e1 and e2 have different hash codes (because default Object.hashCode() depends on memory address).
- They are stored in different buckets.
- Result: Both entries are added, and set size is 2 — which is wrong!
*****************************************




