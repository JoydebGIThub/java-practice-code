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

## Capitalize the first letter of each string in a list.
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Joydeb Biswas", "Kal khanna", "Suparna Shikdar", "Dev Roy");
        System.out.println(list.stream().map(String::toUpperCase).toList());
        
    }
}
```

## From a list of integers, return only the square of odd numbers.
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 4, 6, 7, 8, 9);
        System.out.println(nums.stream().filter(x -> x%2 != 0).map(x -> x*x).toList());
        
    }
}
```

## Flatten a list of comma-separated strings (e.g., "a,b", "c,d") into a list of individual characters.
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a, b", "c, d");
        System.out.println(list.stream().flatMap(word -> Arrays.stream(word.split(", "))).toList());
        
    }
}
```

## Sort a list of strings by the number of vowels in each word.
```java
import java.util.*;
import java.util.stream.*;
class Main {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "banana", "fig", "cherry");
        System.out.println(list.stream()
        .sorted(Comparator.comparingInt(s -> (int) s.chars()
            .filter(c -> "aeiouAEIOU".indexOf(c)>=0)
            .count()
            )
        )
        .toList()
        );//[fig, cherry, apple, banana]
        String name = "Joydeb";
        System.out.println(name.chars().mapToObj(c -> (char) c).filter(c -> "aeiouAEIOU".indexOf(c) >=0 ).toList());//[o, e]
        
        
    }
}
```

## Group a list of strings by their length (using Collectors.groupingBy).
```java
import java.util.*;
import java.util.stream.*;
class Main {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "banana", "fig", "cherry");
        System.out.println(list.stream().collect(Collectors.groupingBy(String::length)));
    }
}
```

## Count how many strings in the list start with the same first character.
```java
import java.util.*;
import java.util.stream.*;
class Main {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "apricot", "banana", "blueberry", "cherry");
        System.out.println(list.stream().filter(s -> !s.isEmpty()).collect(Collectors.groupingBy(s -> s.charAt(0), Collectors.counting())));
    }
}
```

## Remove duplicate strings ignoring case.
```java
import java.util.*;
import java.util.stream.*;
class Main {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "apricot", "banana", "blueberry", "cherry", "Apple", "Cherry");
        System.out.println(list.stream()
        .map(String::toLowerCase)
        .distinct()
        .toList());//[apple, apricot, banana, blueberry, cherry]
        
        System.out.println(list.stream()
        .collect(Collectors.toCollection(()-> new TreeSet<>(String.CASE_INSENSITIVE_ORDER))).stream().toList());//[apple, apricot, banana, blueberry, cherry]
    }
}
```

## Given a list of strings, return only palindromes.
```java
import java.util.*;
import java.util.stream.*;
class Main {
    public static void main(String[] args) {
        List<String> list = List.of("appa", "aprrpt", "bananab", "blueberry", "cherry", "Appa", "Cherry");
        System.out.println(list.stream()
        .filter(word -> Arrays.stream(word.split("")).reduce((a, b) -> b+a).orElse("").equalsIgnoreCase(word))
        .toList()
        ); //[appa, bananab, Appa]
        
        System.out.println(list.stream()
        .filter(x -> x.equalsIgnoreCase(new StringBuilder(x).reverse().toString()))
        .toList()
        );//[appa, bananab, Appa]
        String a = "Joydeb";
        System.out.println(Arrays.stream(a.split("")).reduce((i, j)-> j+i).orElse(""));
    }
}
```

## Filter out names that start and end with the same letter.
```java
import java.util.*;
import java.util.stream.*;
class Main {
    public static void main(String[] args) {
        List<String> list = List.of("appa", "aprrpt", "bananab", "blueberry", "cherry", "Appa", "Cherry");
        System.out.println(list.stream().filter(x -> x.charAt(0) == x.charAt(x.length() - 1)).toList());//[appa, bananab]
    }
}
```
## Limit the list to the first 5 distinct elements in uppercase.
```java
import java.util.*;
import java.util.stream.*;
class Main {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "apple", "banana", "blueberry", "cherry", "Apple", "Cherry");
        System.out.println(list.stream().map(String::toUpperCase).distinct().limit(5).toList()); //[APPLE, BANANA, BLUEBERRY, CHERRY]
    }
}
```

