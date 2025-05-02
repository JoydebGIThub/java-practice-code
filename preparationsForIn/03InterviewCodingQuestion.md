## 
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> str = Arrays.asList("apple", "banana", "pear","grape");
        str
        .stream()
        .collect(Collectors.groupingBy(String::length))
        .forEach( (x, y) -> System.out.println(x + " " +y));
        //System.out.println(str);

    }
}
```
Output: 
```
4 [pear]
5 [apple, grape]
6 [banana]
```
**************************************************************
#
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> str = Arrays.asList("Hello", " ", "world","!");
        System.out.println(str
        .stream()
        .collect(Collectors.joining()));
        //System.out.println(str);

    }
}
```
```
Hello world!
```
