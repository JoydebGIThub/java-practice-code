# Coding question:
## Write a function to reverse a string
### Solution no: 1
```java
class Main {
    public static void main(String[] args) {
        String a= "I like Java";
        String b="";
        char c;
        for(int i=0; i< a.length(); i++){
            c= a.charAt(i);
            b= c+b;
        }
        System.out.println("reverse string is: "+b);
    }
}
```
### Output:
```
reverse string is: avaJ ekil I
```

### Solution no: 2
```java
class Main {
    public static void main(String[] args) {
        String a= "I like Java";
        StringBuilder b= new StringBuilder();
        b.append(a);
        b.reverse();
        System.out.println("reverse string is: "+b);
    }
}
```

### Output:
```
reverse string is: avaJ ekil I
```

### Solution no: 3
```java
class Main {
    public static void main(String[] args) {
        String a= "I like Java";
        for(int i=a.length()-1; i>=0; i--){
            System.out.print(a.charAt(i));
        }
    }
}
```
### Output:
```
avaJ ekil I
```

### Solution no: 4
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        String a= "I like Java";
        List<Character> l= new ArrayList<>();
        for(int i=0; i< a.length(); i++){
            l.add(a.charAt(i));
        }
        Collections.reverse(l);
        Iterator lt= l.iterator();
        while(lt.hasNext()){
            System.out.print(lt.next());
        }
        
    }
}
```
### Output:
```
avaJ ekil I
```