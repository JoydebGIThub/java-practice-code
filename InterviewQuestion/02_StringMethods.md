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
