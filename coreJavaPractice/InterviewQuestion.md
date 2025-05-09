# You have a String "I Like Java".
## Only print the non-repetative character value:
### Using stream:
```java
package coreJavaPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        String a= "I like java";
        a= a.replace(" ", "").toLowerCase();
        List<Character> list= new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            char c= a.charAt(i);
            list.add(c);
        }
        System.out.println(list);
        // list.stream().filter();
        list.stream()
            .collect(Collectors.groupingBy(i->i, Collectors.counting())).forEach((key, value)->{
                if (value<2) {
                    System.out.print(key+",");
                }
                
            });
            System.out.println();
    }
}

```
### Output:
```
e,v,j,k,l,
```
*******
### Using normal for loop
```java
package coreJavaPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        String a= "I like java";
        a= a.replace(" ", "").toLowerCase();
        
        for (int i = 0; i < a.length(); i++) {
            char c= a.charAt(i);
            if (a.indexOf(c) == a.lastIndexOf(c)) {
                System.out.print(c+",");
            }
        }
    }
}

```

### Output:
```
l,k,e,j,v,
```
***
### Using stream
```java
class Main {
    public static void main(String[] args) {
        String a = "I Like Java";
        a= a.toLowerCase();
        String k = a;
        System.out.println(a.chars()
            .mapToObj(c -> (char) c)
            .filter(x -> k.indexOf(x) == k.lastIndexOf(x))
            .toList()
            );//[l, k, e, j, v]
    }
}
```
### Output:
```
[l, k, e, j, v]
```
***
### if the Question is to print the unique value
```java
package coreJavaPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        String a= "I like java";
        a= a.replace(" ", "").toLowerCase();
        List<Character> list= new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            char c= a.charAt(i);
            list.add(c);
        }

        list.stream().distinct().forEach(System.out::print);
            System.out.println();
       
    }
}

```
### Output:
```
ilkejav
```
### Using set
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
        String a= "I like Java";
        a=a.toLowerCase();
        a=a.replace(" ","");
        Set<Character> l= new LinkedHashSet<>();
        for(int i=0; i<a.length();i++){
            char ch= a.charAt(i);
            l.add(ch);
            // System.out.println(ch);
        }
        System.out.println(l);
    }
    
}
```

### Output
```
[i, l, k, e, j, a, v]
```
