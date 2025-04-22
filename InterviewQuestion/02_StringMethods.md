## String methods:
### .length():
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        String a= "Joydeb Biswas";
        System.out.println("The String is: "+a);//Joydeb biswas
        System.out.println("The length of the String is: "+a.length());// 13
    }
}
```
### .charAt():
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        String a= "Joydeb Biswas";
        System.out.println("The String is: "+a);//Joydeb biswas
        System.out.println("The character at the 2nd position is: : "+a.charAt(1));//o
    }
}
```
### indexOf() && lastIndexOf()
```java
// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class Main {
    public static void main(String[] args) {
        String a= "Joydeb biswas";
        System.out.println("The last index value of the b char is: "+a.lastIndexOf('b'));//7
        System.out.println("The first index value of the b char is: "+a.indexOf('b'));//5
    }
}
```
### .substring()
```java

import java.util.*;
class Main {
    public static void main(String[] args) {
        String a= "   Joydeb Biswas   ";
        System.out.println("The substring is: "+a.substring(0,5));//    Jo  
    }
}
```
### .toUpperCase() && .toLowerCase()
```java

import java.util.*;
class Main {
    public static void main(String[] args) {
        String a= "   Joydeb Biswas   ";
        System.out.println("The String in upperCase: "+ a.toUpperCase());//JOYDEB BISWAS
        System.out.println("The String is lowerCase: "+a.toLowerCase());//joydeb biswas
    }
}
```

### .trim() && .replace(" ","")
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        String a= "   Joydeb Biswas   ";
        System.out.println("Remove starting and ending gap: "+a.trim());//Joydeb Biswas
        System.out.println("Remove all the gaps: "+a.replace(" ",""));//JoydebBiswas
    }
}
```
### .startsWith() && .endsWith()
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        String a= "   Joydeb Biswas   ";
        System.out.println("Check startsWith: "+a.startsWith(" "));//true
        System.out.println("Check endsWith: "+a.endsWith("s"));// false

    }
}
```
### .compareTo() && a.equals()
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        String a= "   Joydeb Biswas   ";        
        String b="Joydeb Biswas";
        System.out.println("Compare a and b: "+a.compareTo(b));// -42 becaues it false. if true then give 0
        System.out.println("Check the equals between Strings: "+a.equals(b));// false
    }
}
```
### .concat() && + && .join()
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        String a= "   Joydeb Biswas   ";
        String b="Joydeb Biswas";
        System.out.println("Concat the String: "+a.concat(b));//    Joydeb Biswas   Joydeb Biswas
        System.out.println("Combine to string: "+ a+b);//    Joydeb Biswas   Joydeb Biswas

        String fruits = String.join(" ", "Orange", "Apple", "Mango");
        String c="Joydeb";
        String d="Biswas";
        System.out.println(fruits);//Orange Apple Mango
        System.out.println(String.join(", ", c , d));//Joydeb, Biswas  
    }
}
```
### .toString()
```java
public class Main {
  public static void main(String[] args) {
    String myStr = "Hello, World!";
    System.out.println(myStr.toString());//Hello, World! return the string itself
  }
}
```
### .isEmpty()
```java
public class Main {
  public static void main(String[] args) {
    String myStr1 = "Hello";
    String myStr2 = "";
    System.out.println(myStr1.isEmpty());
    System.out.println(myStr2.isEmpty());
  }
}
```
