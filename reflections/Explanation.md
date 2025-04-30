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
     System.out.println(myCat.getClass());//class Cat
  }
}
```






