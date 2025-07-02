## Given a list of integers, return a list with each element squared.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 4, 5, 7, 8);
        System.out.println(list.stream().map(x -> x*x).toList());
    }
}
```

## From a list of strings, return only those that start with the letter "A".
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> names= Arrays.asList("Roushan","Joydeb","Arko","Aman","Joy");
        System.out.println(names.stream().filter(x -> x.startsWith("A")).toList());
    }
}
```

## Remove duplicate elements from a list of strings using streams.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "orange","banana","apple","orange");
        System.out.println(fruits.stream().distinct().toList());
    }
}
```

## Sort a list of integers in descending order using streams.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(1, 2, 5, 6, 8, 4, 3);
        System.out.println(a.stream().sorted(Comparator.reverseOrder()).toList());
        System.out.println(a.stream().sorted((x, y) -> y - x).toList());
    }
}
```

## Given a list of names, return a list of names with more than 3 characters.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> list = List.of("Amy", "John", "Al", "Steve");
        System.out.println(list.stream().filter(x -> x.length() > 3).toList());
    }
}
```

## Given a list of strings, convert them to uppercase and remove duplicates.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "orange","banana","apple","orange");
        System.out.println(fruits.stream().map(String::toUpperCase).distinct().toList());
    }
}
```

## From a list of List<String>, flatten it to a single list of strings using flatMap.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<List<String>> listOfList = Arrays.asList(
            Arrays.asList("Joydeb","Biswas"),
            Arrays.asList("Suparna"),
            Arrays.asList("Pradip","Kumar","Biswas")
            );
        System.out.println(listOfList.stream().flatMap(List::stream()).toList());
    }
}
```

## Skip the first 3 elements of a list and return the rest.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(1, 2, 5, 6, 8, 4, 3);
        System.out.println(a.stream().skip(3).toList());
    }
}
```

## Limit the result to the first 4 elements of a stream.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(1, 2, 5, 6, 8, 4, 3);
        System.out.println(a.stream().limit(4).toList());
    }
}
```

## From a list of words, return a list of unique characters (e.g., "cat", "dog" → 'c', 'a', 't', 'd', 'o', 'g').
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> words= Arrays.asList("cat", "dog");
        System.out.println(words.stream().flatMap(word -> word.chars().mapToObj(c -> (char) c)).toList());
    }
}
```

## Given a list of integers, double each value and return only those greater than 10.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(1, 2, 5, 6, 8, 4, 3);
        System.out.println(a.stream().map(x -> x*x).filter(x -> x>10).toList());
    }
}
```

## Sort a list of strings by their length.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> list = List.of("Amy", "John", "Al", "Steve");
        System.out.println(list.stream().sorted((x, y)-> x.length() - y.length()).toList());
        System.out.println(list.stream().sorted(Comparator.comparingInt(String::length)).toList());
    }
}
```

## Filter a list of email addresses to only include those ending with "@gmail.com".
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> email = Arrays.asList("royr@gmail.com","abs@accenture.com","kal.123@gmail.com");
        System.out.println(email.stream().filter(x -> x.endsWith("@gmail.com")).toList());
    }
}
```

##  Given a list of full names (e.g., "John Doe"), return a list of only the first names.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> name= Arrays.asList("Joydeb Biswas", "John Doe");
        System.out.println(name.stream().map(x -> x.split(" ")[0]).toList());
    }
}
```

##  Given a list of sentences, split each into words and return a flat list of all words.
```java
import java.util.stream.*;
import java.util.*;
class Main {
    public static void main(String[] args) {
        List<String> flat= Arrays.asList("Joydeb Biswas", "I love Java", "I am a full stack developer");
        System.out.println(flat.stream().flatMap(word -> Arrays.stream(word.split(" "))).toList());
    }
}
```

## Find the first non-repetitive character
```java
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
        String str = "I love Java i am a good boy";
        String str2 = str.toLowerCase();

        System.out.println(str2.chars().mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(
                    Function.indentity(),
                    LinkedHashMap::new,
                    Collectors.counting()
                ))
            .entrySet().stream()
            .filter(entry -> entry.getValue() == 1)
            .map(Map.Entry::getKey)
            .findFirst()
            .get()
        );
    }
}

```











