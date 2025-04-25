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
## Solution no: 2
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> n= Arrays.asList("Joydeb", "Riya", "Suparna","Joy");
        n
        .stream()
        .sorted(Comparator.comparingInt(String::length))
        .forEach(i-> System.out.print(i+", "));
    }
}
```
## Output:
```
Joy, Riya, Joydeb, Suparna,
```
## Solution no: 2 print the output in list format
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> n= Arrays.asList("Joydeb", "Riya", "Suparna","Joy");
        System.out.print(n
        .stream()
        .sorted(Comparator.comparingInt(String::length))
        .toList());
    }
}
```
## Output:
```
[Joy, Riya, Joydeb, Suparna]
```
***
# Print the list as per the length in descending order
## Solution no: 1
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> n= Arrays.asList("Joydeb", "Riya", "Suparna","Joy");
        System.out.print(n
        .stream()
        .sorted(Comparator.comparingInt(String::length).reversed())
        .toList());
    }
}
```
## Output:
```java
[Suparna, Joydeb, Riya, Joy]
```

