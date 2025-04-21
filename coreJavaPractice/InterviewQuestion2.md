# Interview Therory Question:
## Q: What is the difference between == and .equals() in java
```
==: Compares object "references (memory address)". For primitives, it compares the actual "values". Think of it asking "Are these the exact same object in memory?"
.equals(): Compares the "content or logical equality" of the objects. The default behavior is the same as ==, but it's meant to be "overridden" by classes to define what "equal content" means for their instance. Think of it as asking "Do these objects have the same meaningful data?"

Note: Use "==" for reference equality and ".equals()" for content equality. Remember to override ".equals() (and hashCode())" in your custom classes
```

```java
// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class Main {
    public static void main(String[] args) {
        //Primitive types
        int num1= 5;
        int num2= 5;
        System.out.println("Primitive comparison (==): "+ (num1==num2)); //true
        
        //Object references (Strings- spacial case of objects)
        String str1="Hello";
        String str2="Hello";
        String str3= new String("Hello");
        System.out.println("String reference comparison (==): "+ (str1==str2)); // true : String constant pool same reference of the address
        System.out.println("String reference comparison (==): "+ (str1==str3)); // false new create new reference instance
        System.out.println("String reference comparison (.equals()): "+ (str1.equals(str2))); //true compare the value inside it
        System.out.println("String reference comparison (.equals()): "+ (str1.equals(str3))); // true
        
        //Custom objects
        Point p1= new Point(1, 2);
        Point p2= new Point(1, 2);
        Point p3= p1;
        System.out.println("Object reference comparison (==): "+ (p1==p2)); //false (different point object)
        System.out.println("Object reference comparison (==): "+ (p1==p3)); // true (same point object)
        System.out.println("Object reference comparison (.equals()): "+ (p1.equals(p2))); // true (same content because equals is overridden)
        
    }
    
}
class Point{
    int x;
    int y;
    
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public boolean equals(Object obj){
        if(this == obj) return true; //same object in memory
        if(obj == null || getClass() != obj.getClass()) return false; // null or different class
        Point other= (Point) obj;
        return x==other.x && y == other.y; // Compare the relevent fields(content)
    }
    
    public int hashCode(){
        return java.util.Objects.hash(x, y); //Important to override hashCode with equals
    }
}
```
