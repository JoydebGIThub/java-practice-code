# ✅ Difference between Base Class and Abstract Class in Java
## 🎯 Base Class (also called Superclass or Parent Class)
- A base class is any class that is extended (inherited) by another class.
- It can be a normal (concrete) class or even an abstract class.
- It may have complete method implementations, constructors, and fields.
- Child classes inherit all non-private fields and methods from the base class.
```java
class Animal {   // Base Class
    public void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {   // Derived Class
    public void bark() {
        System.out.println("Dog is barking");
    }
}

```
## 🎯 Abstract Class
- An abstract class is a special kind of base class that cannot be instantiated.
- It is used to define a common template for its subclasses.
- It can have abstract methods (methods without a body) and concrete methods (methods with a body).
- Abstract classes are designed to be extended by other classes which will implement the abstract methods.
```java
abstract class Shape {   // Abstract Class
    abstract void draw();   // Abstract Method

    public void color() {   // Concrete Method
        System.out.println("Shape has a color");
    }
}

class Circle extends Shape {   // Subclass
    @Override
    void draw() {
        System.out.println("Drawing a circle");
    }
}

```
**********************************
# ✅ Marker Interface in Java
## 🎯 Definition:
A Marker Interface is an empty interface — it has no methods and no fields inside it.
Its purpose is to simply mark or tag a class so that the Java compiler or JVM can treat the class differently based on the presence of that interface.
```java
// Marker Interface
interface Serializable {
    // No methods inside
}

// Class implementing Marker Interface
class Student implements Serializable {
    int id;
    String name;
}

```
Here, by implementing Serializable, we tell the JVM:
- "Hey, this Student object can be serialized!"
- Even though Serializable has no methods, it plays a critical role.
#### 🎯 Popular Examples of Marker Interfaces in Java:
- java.io.Serializable (for serialization)
- java.lang.Cloneable (for cloning objects)
- java.util.RandomAccess (for efficient random access in List implementations)
*********************************
# ✅ How JVM uses Marker Interfaces (like Serializable) internally:
## 🎯 Runtime Behavior:
When you try to serialize an object, Java’s serialization mechanism (especially ObjectOutputStream) checks at runtime whether the object’s class implements the Serializable interface.
It uses Reflection to check "is this object an instance of Serializable?"
- ➡ If yes, it allows serialization.
- ➡ If no, it throws a NotSerializableException.
```java
ObjectOutputStream out = new ObjectOutputStream(fileOut);
if (!(object instanceof Serializable)) {
    throw new NotSerializableException(object.getClass().getName());
}
out.writeObject(object);

```
✨ So even though Serializable has no methods,
just the presence of the interface affects the runtime behavior!
### Without the marker interface
```java
class Student {
    int id;
    String name;
}

// No Serializable implemented
Student s1 = new Student();
ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.txt"));
out.writeObject(s1); // ❌ Throws NotSerializableException
```
### With marker interface:
```java
class Student implements Serializable {
    int id;
    String name;
}
```
"**Marker interfaces in Java don't define behavior but enable JVM to apply behavior dynamically at runtime based on the class's tag.**"
**********************************
# ✅ Interview Question:
Suppose you have the following classes:
```
``
interface MarkerInterface {
}

class A {
}

class B extends A implements MarkerInterface {
}

``
``
B obj = new B();
System.out.println(obj instanceof MarkerInterface);
System.out.println(obj instanceof A);
System.out.println(obj instanceof Object);
``
```
# Q: 👉 What will be the output?
## ✅ Answer:

|Statement | Result | Why?|
|----------|--------|-----|
|obj instanceof MarkerInterface | true | Because B implements MarkerInterface|
|obj instanceof A | true | Because B extends A (inheritance)|
|obj instanceof Object | true | Because all Java classes inherit from Object|

## ✅ Explanation:
- Even though MarkerInterface has no methods, as soon as B implements it, instanceof returns true.
- instanceof checks the type hierarchy — so because B extends A, it’s also considered an instance of A.
- And because everything in Java extends Object (either directly or indirectly), obj instanceof Object will always be true.






