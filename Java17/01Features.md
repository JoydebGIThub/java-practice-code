## Sealed Classes (JEP 409)
- Restrict which classes or interfaces can extend or implement a class
```java
public sealed class Shape permits Circle, Rectangle{}

public final class Circle extends Shape{}
public final class Rectangle extends Shape{}
```

## Pattern Matching for `stitch` (Preview - JEP 406)
- Simplifies `switch` statements with pattern matching.
```java
static String formatterPatternSwitch(Object o){
  return switch(o){
    case Integer i -> "int: " + i;
    case Long l -> "long: " +l;
    case String s -> "string: "+s;
    default -> "unknown";
  };
}
```

## Text Blocks (since Java 15, improved by Java 17)
- Multi-line strings using triple quotes.
```java
String json = """
  {
    "name": "Joydeb",
    "age": "30"
  }
""";
```

