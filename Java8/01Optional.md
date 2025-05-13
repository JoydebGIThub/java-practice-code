## ✅ What is Optional in Java 8?
- **Optional<T> is a container object** which may or may not contain a **non-null value**. It was introduced to **reduce the risk of NullPointerException** and to write more readable and **null-safe code**.
```java
Optional<String> name = Optional.of("John");
```

### 🎯 Why Use Optional?
|Traditional Code	|With Optional|
|-----------------|-------------|
|Risk of null	|Safe & clear intent|
|if (obj != null) checks everywhere	|No more boilerplate null-checks|
|NullPointerException prone	|Controlled with Optional methods|

### ✅ Common Methods in Optional
| Method                | Description                             |
| --------------------- | --------------------------------------- |
| `of(value)`           | Wraps a non-null value (throws if null) |
| `ofNullable(value)`   | Allows null values                      |
| `empty()`             | Returns an empty Optional               |
| `isPresent()`         | Checks if value is present              |
| `get()`               | Returns the value (unsafe if empty)     |
| `ifPresent(Consumer)` | Executes action if value exists         |
| `orElse(default)`     | Returns value or default                |
| `orElseGet(Supplier)` | Lazily returns value or default         |
| `orElseThrow()`       | Throws an exception if empty            |
| `map()`               | Transforms the value if present         |
| `flatMap()`           | Like `map`, but avoids nested Optionals |

### NullPointerException and how to avoid it:
```java
// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class Main {
    public static void main(String[] args) {
        String name = null;
        System.out.println(name); // null
        // System.out.println(name.length());//Exception in thread "main" java.lang.NullPointerException: Cannot
        
        // Optional<String> optionalNull = Optional.of(name);//Exception in thread "main" java.lang.NullPointerException: Cannot
        Optional<String> optionalName = Optional.ofNullable(name);
        System.out.println(optionalName);//Optional.empty
        System.out.println(optionalName.orElse("Default Name"));//Default Name     
    }
}
```

### Using ifPresent():
- Only give the result if the value is present in optional value.
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        String name = null;        
        Optional<String> optionalName = Optional.ofNullable(name);
        optionalName.ifPresent((n)-> System.out.println("Hello "+n));        
    }
}
```
### Output:
```

```

### Using isPresent():
- Using for checking the value present or not:
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        String name = null;
        Optional<String> optionalName = Optional.ofNullable(name);
        System.out.println(optionalName.isPresent());//false   
    }
}
```
### Output:
```
false
```

### map:
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        Optional<String> optionalName = Optional.of("Joydeb");
        Optional<String> upper = optionalName.map(String::toUpperCase);
        System.out.println(upper.orElse("Default Name"));//JOYDEB
    }
}
```

#### if we pass "" then the output will be blank
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        Optional<String> optionalName = Optional.of("");
        Optional<String> upper = optionalName.map(String::toUpperCase);
        System.out.println(upper.orElse("Default Name"));//JOYDEB
    }
}
```

#### Output:
```

```

#### if we pass null as the value then:
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        String name = null;
        Optional<String> optionalName = Optional.ofNullable(name);
        // Optional<String> optionalName = Optional.of(null); // NullPointerException
        Optional<String> upper = optionalName.map(String::toUpperCase);
        System.out.println(upper.orElse("Default Name"));//Default Name
    }
}
```

### Output:
```
Default Name
```

### orElseThrow and empty()
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        Optional<String> optionalName = Optional.empty(); 
        String result = optionalName.orElseThrow(()-> new IllegalArgumentException("Name not present"));
    }
}
```

### Output:
```
Exception in thread "main" java.lang.IllegalArgumentException: Name not present
```

### ⚠️ Things to Avoid
- Don't use `Optional.get()` **without checking** `isPresent()`.
- Avoid using Optional in fields, method parameters, or constructors — use it in return types only.
- Don’t overuse Optional, especially in performance-critical sections.

### 🔹 filter(Predicate<? super T>)
- Returns an `Optional` if the value matches the `predicate`, otherwise **returns empty**.
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        Optional<String> optionalName = Optional.of("Joydeb"); 
        Optional<String> filterName = optionalName.filter(n -> n.startsWith("K"));
        System.out.println(filterName.orElse("No result")); // No result
    }
}
```

### 🔹 flatMap(Function<? super T, Optional<U>>)
- Similar to `map`, but avoids nested `Optional<Optional<U>>`.
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        Optional<String> optionalName = Optional.of("Joydeb"); 
        Optional<Integer> lenth = optionalName.flatMap(n -> Optional.of(n.length()));
        Optional<Integer> lenth2 = optionalName.map(n -> n.length());
        System.out.println(lenth.orElse(0)); //6
        System.out.println(lenth2.orElse(0)); //6
    }
}
```

### 🔹 ifPresentOrElse(Consumer, Runnable) — Java 9+
- Executes action if value present, else runs fallback.
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Joydeb"); 
        name.ifPresentOrElse(
            val -> System.out.println("The Name is: "+val),
            () -> System.out.println("The value is not present")
        );
    }
}
```
### Output:
```
The Name is: Joydeb
```
