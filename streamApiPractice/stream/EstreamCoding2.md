## Given a list of integers, filter out only even number.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 4, 6, 7, 8, 9);
        System.out.println(nums.stream().filter(x -> x%2 == 0).toList());
    }
}
```

## From a list of strings, return only those that contain the letter "e".
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Joydeb", "Kal", "Suparna", "Dev");
        System.out.println(list.stream().filter(x -> x.contains("e")).toList());
    }
}
```
## Convert a list of words to a list of their lengths.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Joydeb", "Kal", "Suparna", "Dev");
        System.out.println(list.stream().map(String::length).toList());
    }
}
```

## From a list of full names, return a list of last names.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Joydeb Biswas", "Kal khanna", "Suparna Shikdar", "Dev Roy");
        System.out.println(list.stream().map(word -> word.split(" ")[1]).toList());    
    }
}
```

## Given a list of strings, remove all empty or blank strings.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
       List<String> list = List.of("apple", "", "  ", "banana", "cherry");
        System.out.println(list.stream().filter(x -> !x.isBlank()).toList());
        System.out.println(list.stream().filter(x-> x != null && !x.trim().isEmpty()).toList());
    }
}
```







