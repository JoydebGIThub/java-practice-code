## What is reflections in Java?
Ans: Java **reflection** is a powerful **runtime mechanism** that allows a Java program to **inspect and manipulate** the properties (like class, methods, fields, constructors) of objects and classes at **runtime**. It provides the ability to discover and interact with code that might not be known at compile time.
### Explanation:
```java
//normar pojo class
class Cat{
    private final String name;
    private int age;
    public Cat(String name, int age){
        this.name=name;
        this.age=age;
    }
    public String getName(){
        return name;
    } 
    public int getAge(){
        return age;
    }
    private void meow(){
        System.out.println("meow"); 
    }
    private static void play(){
        System.out.println("My Cat is playing");
    }
    public void eat(String food){
        System.out.println("My cat: "+name+" eat: "+food);
    }
}
```
### Want to access the class by using reflection
```java
public class Main{
  public static void main(String[] args){
    System.out.println(Class.forName("Cat"));//class Cat
  }
}
```
Or,
```java
public class Main{
  public static void main(String[] args){
     Cat myCat= new Cat("jj", 12);
     System.out.println(myCat.getClass());//class Cat
  }
}
```
### Get all the fields of that Class
```java
import java.lang.reflect.Field;
public class Main{
  public static void main(String[] args){
     Cat myCat= new Cat("jj", 12);
     Field[] catField= myCat.getClass().getDeclaredFields(); // for using the Field we need to import java.lang.reflect
     for(Field f: catField){
        System.out.println(f.getName()); // print all the fields on the Cat class
     }
  }
}
```
### Get all the methods of that Class
```java
import java.lang.reflect.Method;
public class Main{
  public static void main(String[] args){
     Cat myCat= new Cat("jj", 12);
     Method[] catMethod= myCat.getClass().getDeclaredMethods(); // for using the Method we need to import java.lang.reflect
     for(Method m: catMethod){
        System.out.println(m.getName()); // print all the methods on the Cat class
     }
  }
}
```
### Change the value in the private final field
```java
import java.lang.reflect.Field;
public class Main{
  public static void main(String[] args){
     Cat myCat= new Cat("jj", 12);
     Field[] catField= myCat.getClass().getDeclaredFields(); // for using the Field we need to import java.lang.reflect
     for(Field f: catField){
        System.out.println(f.getName()); // print all the fields on the Cat class
        if(f.getName == "name"){
            f.setAccessible(true); // it will help us to access the private final value
            f.set(myCat, "Jo Jo"); // this will help us to set the value
        }
     }
  }
}
```
### run the methods from Cat class:
```java
import java.lang.reflect.Method;
public class Main{
  public static void main(String[] args){
     Cat myCat= new Cat("jj", 12);
     Method[] catMethod= myCat.getClass().getDeclaredMethods(); // for using the Method we need to import java.lang.reflect
     for(Method m: catMethod){
        System.out.println(m.getName()); // print all the methods on the Cat class
        if(m.getName()=="meow"){
            //meow is a private method so
            m.setAccessible(true);
            //now to call the method.
            m.invoke(myCat);
        }
     }
  }
}
```
### run a method with paramatter
```java
import java.lang.reflect.Method;
public class Main{
  public static void main(String[] args){
     Cat myCat= new Cat("jj", 12);
     Method[] catMethod= myCat.getClass().getDeclaredMethods(); // for using the Method we need to import java.lang.reflect
     for(Method m: catMethod){
        System.out.println(m.getName()); // print all the methods on the Cat class
        if(m.getName()=="eat"){
            //meow is a public method so
            //now to call the method and pass the parametter.
            m.invoke(myCat, "milk");
        }
     }
  }
}
```
### run a static method
```java
import java.lang.reflect.Method;
public class Main{
  public static void main(String[] args){
     Cat myCat= new Cat("jj", 12);
     Method[] catMethod= myCat.getClass().getDeclaredMethods(); // for using the Method we need to import java.lang.reflect
     for(Method m: catMethod){
        System.out.println(m.getName()); // print all the methods on the Cat class
        if(m.getName()=="play"){
            //meow is a private method so
            m.setAccessible(true);
            //now to call the method.
            m.invoke(null); // its a static method so no need to pass the object/instance
        }
     }
  }
}
```



