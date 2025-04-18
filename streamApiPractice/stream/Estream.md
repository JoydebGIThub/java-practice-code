# Stream API

## Basic Stream Operations:
```java
package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;

public class Estream {
    public static void main(String[] args) {
        List<String> names= Arrays.asList("Joydeb", "Suparna", "Popo", "Bob");
        names
            .stream()
            .filter(name-> name.startsWith("J"))
            .map(String::toUpperCase)
            .forEach(System.out::println);
    }
}

```

### Explanation:
```
a. .stream()
    1. Converts the names list into a Stream.
    2. A Stream is a sequence of elements supporting functional-style operations.

b. .filter(name -> name.startsWith("J"))
    1. Filters the stream using a predicate.
    2. Only elements (names) that start with the letter "J" will pass through.
    3. In this case, it filters the list to keep only "Joydeb".

c. .map(String::toUpperCase)
    1. Transforms each remaining string to uppercase.
    2. String::toUpperCase is a method reference, which is shorthand for name -> name.toUpperCase().

d. .forEach(System.out::println)
    1. Terminal operation that iterates over each remaining element and prints it.
    2. System.out::println is a method reference to the println() method.
```
### Output:
```
JOYDEB
```

***
## Sum of Even Numbers

```java
package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Estream2 {
    public static void main(String[] args) {
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 3, 2, 6, 5, 10);
        int result =num
            .stream()
            .filter(n -> n %2 == 0)
            .mapToInt(Integer:: intValue)
            .sum();
        System.out.println("Sum: "+result);

        //alternative way
        int result2= num
            .stream()
            .filter(n-> n%2==0)
            .collect(Collectors.summingInt(Integer::intValue));
        System.out.println(result2);
    }
}

```
### Output:
```
Sum: 24
24
```

## Collecting into a List
```java
package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Estream3 {
    public static void main(String[] args) {
        List<String> names= Arrays.asList("Joydeb", "Joy","Suparna", "Bob");
        List<String> filtered= names
                                .stream()
                                .filter(n->n.contains("J"))
                                .collect(Collectors.toList());
        System.out.println(filtered);
    }
}

```
### Output:
```
[Joydeb, Joy]
```

## Group by using collectors:
```java
package streamApiPractice.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Person{
    String name;
    String city;

    Person(String name, String city){
        this.name=name;
        this.city=city;
    }
    public String getName(){
        return name;
    }
    public String getCity(){
        return city;
    }
}
public class Estream4 {

    public static void main(String[] args) {
        List<Person> people= new ArrayList<>(
            Arrays.asList(
                new Person("Joydeb", "Kolkata"),
                new Person("Suparna", "Bangalore"),
                new Person("Joy", "Delhi"),
                new Person("Popo", "Bangalore"),
                new Person("Bob", "Kolkata"),
                new Person("Roy", "Mumbai")
            )
        );
        Map<String, List<String>> groupingBycity= people.stream()
            .collect(Collectors.groupingBy(Person::getCity, 
            Collectors.mapping(Person::getName, Collectors.toList())));
        
        System.out.println(groupingBycity);

         people.stream()
            .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()))
            .forEach((s, l)->System.out.println(s+"-"+l));
    }
}
```
### output:
```
{Delhi=[Joy], Kolkata=[Joydeb, Bob], Mumbai=[Roy], Bangalore=[Suparna, Popo]}
Delhi-1
Kolkata-2
Mumbai-1
Bangalore-2
```

## Sort a List using .sorted()
```java
package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;

public class Estream5 {

    public static void main(String[] args) {
        List<String> names=Arrays.asList("Joydeb", "Aman","Roy","Suparna","Popo");
        names
            .stream()
            .sorted()
            .forEach(System.out::println);
    }
}
```
### Output:
```
Aman
Joydeb
Popo
Roy
Suparna
```

## Find first Element

```java
package streamApiPractice.stream;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Estream6 {
    public static void main(String[] args) {
        Optional<Integer> first= Arrays.asList(10, 20, 30, 40)
            .stream()
            .findFirst();
        first.ifPresent(System.out::println);

        List<Integer> list = Arrays.asList(1, 2, 3, 5, 6, 7, 8, 9);
        list    
            .stream()
            .findFirst()
            .ifPresent(System.out::println);
            
    }
}

```

### Output:
```
10
1
```

## Convert a List of String to Uppercase
```java
package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Estream7 {
    public static void main(String[] args) {
        List<String> names= Arrays.asList("Joydeb","Suparna","Popo");
        names.stream()
            .map(String::toUpperCase)
            .forEach(System.out::println);

        List<String> namesInUpper= names
            .stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println(namesInUpper);
    }
}

```

### Output:
```
JOYDEB
SUPARNA
POPO
[JOYDEB, SUPARNA, POPO]
```

## Count Elements Matching a Condition
```java

package streamApiPractice.stream;

import java.util.Arrays;

public class Estream8 {

    public static void main(String[] args) {
        long count= Arrays.asList("one", "two", "three")
            .stream()
            .filter(n -> n.length()>3)
            .count();
        System.out.println("Words with length > 3: "+count);
    }
}
```

### Output:
```
Words with length > 3: 1
```

## map and FlatMap
```java
package streamApiPractice.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Estream9 {
    public static void main(String[] args) {
        List<List<String>> nested= Arrays.asList(
            Arrays.asList("a","b"),
            Arrays.asList("c","d")
        );
        List<String> flat= nested.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        System.out.println(flat);
        
        List<List<String>> map= nested.stream()
        .map(list -> new ArrayList<>(list))
        .collect(Collectors.toList());
        
        System.out.println(map);

    }
}

```

### Output:
```
[a, b, c, d]
[[a, b], [c, d]]
```

## Do the addition using .reduce
```java
package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;

public class Estream10 {
    public static void main(String[] args) {
        List<Integer> num= Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum=num
            .stream()
            .reduce(0, (a,b)-> a+b);
        System.out.println("The result is: "+sum);

    }
}

```
### Output:
```
The result is: 21
```

## Get the unique value using .distinct()
```java
package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;

public class Estream11 {
    public static void main(String[] args) {
        List<Integer> nums= Arrays.asList(1, 1, 2, 7, 2, 1, 2, 8);
        nums
            .stream()
            .distinct()
            .forEach(System.out::println);
    }
}

```
### Output:
```
1
2
7
8
```