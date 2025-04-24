# You have a String "I Like Java".
## Only print the non-repetative character value:
### Solution no: 1
```java
import java.util.*;
import java.util.stream.Collectors;
class Main {
    public static void main(String[] args) {
        String n= "i like java";
        
        // List<String> c= Arrays.asList(n.split(" "));
        // System.out.print(c);//[I, Like, Java]
        // List<Character> d= 
                            n
                            .replace(" ","")
                            .toLowerCase()
                            .chars()
                            .mapToObj(ch-> (char)ch)
                            .filter(i -> n.indexOf(i) == n.lastIndexOf(i))
                            .forEach(System.out::print);
                            // .collect(Collectors.toList());
                            // .toList();
        
        // System.out.print(d);
    }
}

```
### Output:
```
lkejv
```
#### Explanation:
1. n.chars(): This returns an IntStream of the character codes (Unicode values) of the characters in the string.
2. .mapToObj(c -> (char) c): This step converts each integer character code back into a char object. The lambda expression c -> (char) c performs this casting.
3. .collect(Collectors.toList()): This terminal operation collects the stream of Character objects into a new List<Character>.
### Solution no: 2
```java
import java.util.*;
import java.util.stream.Collectors;
class Main {
    public static void main(String[] args) {
        String n= "I like java";
        String k = n.replace(" ","").toLowerCase();
        k
        .chars()
        .mapToObj(ch-> (char)ch)
        .filter(i -> k.indexOf(i) == k.lastIndexOf(i))
        .forEach(System.out::print);
    }
}

```
#### Output:
```
lkejv
```
### Solution no: 3
```java
import java.util.*;
import java.util.stream.Collectors;
class Main {
    public static void main(String[] args) {
        String n= "I like java";
        String k = n.replace(" ","").toLowerCase();
        for(int i=0;i< k.length();i++){
            char ch= k.charAt(i);
            if(k.indexOf(ch) == k.lastIndexOf(ch))
                System.out.print(ch);
        }
    }
}

```
#### Output:
```
lkejv
```

### Solution no 4:
```java
import java.util.*;
import java.util.stream.Collectors;
class Main {
    public static void main(String[] args) {
        String n= "I like java";
        String k = n.replace(" ","").toLowerCase();
        k
        .chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(i->i, Collectors.counting()))
        .forEach((i, j)->{
            if(j<2){
                System.out.print(i);   
            }
        });
    }
}

```
#### Output:
```
evjkl
```