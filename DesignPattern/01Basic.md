## Design Pattern:
- Design patterns are the well proved solution of commonly occurring problems in software design.
- Design patters represents and idea.

## Categorization Design Pattern:
- Creational design pattern
  - Factory Pattern
  - Builder Pattern
  - Singleton Pattern
- Structural Design Pattern
  - Proxy pattern.
  - Adaptor pattern.
- Behavioral Design Pattern
  - Observer Pattern State Pattern.
  - Iterator Pattern

## Points:
- **Christopher Alexander** was the first person who invented all the above design patterns in 1977.
- But later the **Gang of Four - Design patterns, elements of resuable object-oriented software** book was written by a group of four persons named as **Erich Gamma, Richard Helm, Ralph Johnson and John vlissides in 1995**.

## We want to create a object only one time and use that everywhere then we use Singleton design pattern its a creational design pattern.
- What we create a **App and connect it with the DB** then only one time the object needs to be created means we create the connection one time so we use singleton.

### Lesy way to create single object:
- means when the client need it and call the method then only the object will be created.
- It's good for **single thread**
```java
// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class Main {
    public static void main(String[] args) {
        Samosa.getSamosa();
        Samosa.getSamosa();
        Samosa.getSamosa();
        Samosa.getSamosa();
        
        Samosa samosa1 = Samosa.getSamosa();
        System.out.println(samosa1.hashCode());
        
        Samosa samosa2 = Samosa.getSamosa();
        System.out.println(samosa2.hashCode());
    }
}
class Samosa{
    //create fielf to store object is private
    private static Samosa samosa;
    //Constructor needs to be private in singleton
    private Samosa(){
        System.out.println("Samosa");
    }
    //Object create with the help of method:
    //Lasy way of creating single object means when the client needs it and call for it then only the object will be created.
    public static Samosa getSamosa(){
        //object of this class
        if(samosa == null){
            samosa = new Samosa();
        }
        return samosa;
    }
}
```
### Output:
```
Samosa
705927765
705927765
```
### For multithread code we need to add synchronized in the method.
- if we use method synchronization then it will effect rest of the code inside the method. We only need to synchronized the creation of the object
```java
public synchronized static Samosa getSamosa(){
        //object of this class
        if(samosa == null){
            samosa = new Samosa();
        }
        return samosa;
    }
```

## synchronized block
```java
public static Samosa getSamosa(){
        //object of this class
    if(samosa == null){
      synchronized(Samosa.class){
        if(samosa == null){
              samosa = new Samosa();
          }
      }
    }      
  return samosa;
}
```

### Eager way of creating singleton object.
- Object will be creating in advance before calling the method by the client its a problem if we not use the object still it create the object when the class load
```java
// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class Main {
    public static void main(String[] args) {
        Jalebi.getJalebi();
        Jalebi.getJalebi();
        Jalebi.getJalebi();
        Jalebi.getJalebi();
        
        Jalebi jalebi1 = Jalebi.getJalebi();
        System.out.println(jalebi1.hashCode());
        
        Jalebi jalebi2 = Jalebi.getJalebi();
        System.out.println(jalebi2.hashCode());
    }
}


class Jalebi{
    //Egar way of creating singleton object.
    private static Jalebi jalebi = new Jalebi(); // object is already creating here
    private Jalebi(){}
    public static Jalebi getJalebi(){
        return jalebi;
    }
    
}
```
### Output:
```
705927765
705927765
```

*************************************************************************************************************************
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        // Singleton s = Singleton.getConstructor();
        // System.out.println(s.hashCode());
        // Singleton s2 = Singleton.getConstructor();
        // System.out.println(s2.hashCode());
        
        Thread t1 = new Thread(()->{
            Singleton s = Singleton.getConstructor();
            System.out.println(s.hashCode());
        });
        Thread t2 = new Thread(()->{
            Singleton s2 = Singleton.getConstructor();
            System.out.println(s2.hashCode());
        });
        
        t1.start();
        t2.start();
    }
}
class Singleton{
    private static Singleton instance;
    private Singleton(){
        System.out.println("Singleton");
    }
    
    // public static Singleton getConstructor(){
    //     if(instance == null){
    //         instance = new Singleton();
    //     }
    //     return instance;
    // }
    
    // public synchronized static Singleton getConstructor(){
    //     if(instance == null){
    //         instance = new Singleton();
    //     }
    //     return instance;
    // }
    
    public static Singleton getConstructor(){
        if(instance == null){
            synchronized(Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
