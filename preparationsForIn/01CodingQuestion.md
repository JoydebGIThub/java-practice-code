## Q: Write a program to find the list of unique words from a senteces and sorted alphabetically using Java 8?
```java
import java.util.*;
public class ExecutorServiceExample {
    public static void main(String[] args) {
    String str = "Java is fun and java is powerful";
    List<String> list = Arrays
                        .stream(str.split(" "))
                        .map(String::toLowerCase)
                        .distinct()
                        .sorted()
                        .toList();
    System.out.println(list);
    }
}
```
