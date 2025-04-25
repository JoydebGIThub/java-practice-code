# Write a program to sort a list of strings by their lengths
## Solution no: 1
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> n= Arrays.asList("Joydeb", "Riya", "Suparna","Joy");
        Collections.sort(n, Comparator.comparingInt(String::length));
        System.out.println(n);
    }
}
```
## Output:
```
[Joy, Riya, Joydeb, Suparna]
```
