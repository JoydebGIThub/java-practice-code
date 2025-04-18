# IS-A Relationship (Inheritance)
## What it means:
```txt
    Class B IS-A Class A, it means the Class B is specialized version of Class A.
    This is inheritance - B inherites A's characteristics and behaviours 
```
## Real-world Analogy:
```txt
    1. All birds can fly (generally), eat, and have feathers.
    2. A sparrow can do all that, plus it might have its own specific behavior.
```

```java
class Bird{
    void fly(){
        System.out.println("Birds can fly");
    }
    void eat(){
        System.out.println("Bird eats insects");
    }
}

class Sparrow extends Bird{
    void chirp(){
        System.out.println("Sparrow chirps");
    }
}
```
***
***
# HAS-A Relationship (Composition / Aggregation)
## What It Means:
```txt
When we say Class A HAS-A Class B, it means that CLass A contains or uses an instance of Class B. This is "composition" - it's a "part-of" or "uses-a" relationship
```
## Real-world Analogy:
```txt
A Car HAS-A Engine
    1. A car isn't an engine
    2. But it "contains" or "uses" an engine
    3. Engine is a separate class; Car simply uses it.
```

```java
class Engine{
    void start(){
        System.out.println("Engine Start");
    }
}

class Car{
    private Engine engine; //Car HAS-A Engine
    Car(){
        engine= new Engine();//Composition
    }

    void startCar(){
        engine.start(); //Car uses Engine
        System.out.println("Car is running");
    }
}
```
### Key Difference:
***
| Aspect              | IS-A (Inheritance)                     | HAS-A (Composition)                          |
|---------------------|----------------------------------------|----------------------------------------------|
| Relationship Type   | "is a" (Dog IS-A Animal)               | "has a" (Car HAS-A Engine)                   |
| Reusability         | Shares behavior through superclass     | Uses objects to reuse functionality          |
| Tightness           | Tightly coupled                        | Loosely coupled                              |
| Changes Propagation | Changes in superclass affect subclasses| Less likely to affect containing class       |
| Use Case            | When you need a true hierarchical relationship | When classes are independent but collaborate |
| Flexibility         | Less flexible (tied to one parent)     | More flexible (can change internal objects)  |

***
### Aggregation VS Composition:
+ Composition: Strong HAS-A (Car needs Engine)
+ Aggregation: Weak HAS-A (Department has Students, but Students can exist without Department)  

#### Composition:
```java
class Heart{
    void beat(){
        System.out.println("Heart is beating");
    }
}
class Human{
    private Heart heart;

    Human(){
        //Composition: creating Heart inside Human
        this.heart = new Heart();
    }

    void start(){
        heart.beat();// Delegation
        System.out.println("Human is alive");
    }
}
```

+ Heart object is created within the Human constructor.
+ It cannot be accessed independently
+ If the Human object is destroyed, the Heart is also gone
***
#### Aggregation:
```java
class Student{
    String name;
    Student(String name){
        this.name=name;
    }
    void display(){
        System.out.println("Student name: "+name);
    }
}

class Department{
    private Student student;

    //Aggregation: Student is passed from outside
    Department(Student student){
        this.student= student;
    }
    void show(){
        student.display();
    }
}
```
+ Student is created outside the Department
+ The lifetime of Student is independent of Department
+ Student can be resuded by multiple department.
*************
