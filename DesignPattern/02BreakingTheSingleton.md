## Breaking the singleton:
- Using **refelection UI** we can change the properites from private to public dynamically on runtime. So now try to the singleton:
```java

import java.lang.reflect.*;
class Main {
    public static void main(String[] args) throws Exception {
        Samosa.getSamosa();
        Samosa.getSamosa();
        Samosa.getSamosa();
        Samosa.getSamosa();
        
        Samosa samosa1 = Samosa.getSamosa();
        System.out.println(samosa1.hashCode());
        
        Constructor<Samosa> constructor = Samosa.class.getDeclaredConstructor();
        
        constructor.setAccessible(true);
        Samosa samosa2= constructor.newInstance();
        System.out.println(samosa2.hashCode());
    }
}
class Samosa{
    private static Samosa samosa;
    private Samosa(){
        System.out.println("Samosa");
    }
    public static Samosa getSamosa(){
        if(samosa == null){
            samosa = new Samosa();
        }
        return samosa;
    }
}

```
### Output:
```
Samosa
705927765
Samosa
1442407170
```

## To prevent it:
- Solution 1: if object is there ==> throw exception from inside constructor
```java

import java.lang.reflect.*;
class Main {
    public static void main(String[] args) throws Exception {

        Samosa samosa1 = Samosa.getSamosa();
        System.out.println(samosa1.hashCode());
        
        Constructor<Samosa> constructor = Samosa.class.getDeclaredConstructor();
        
        constructor.setAccessible(true);
        Samosa samosa2= constructor.newInstance();
        System.out.println(samosa2.hashCode());
    }
}
class Samosa{
    private static Samosa samosa;
    private Samosa(){
        if(samosa != null){
            throw new RuntimeException("You are trying to break singleton constructor");
        }
    }
    public static Samosa getSamosa(){
        if(samosa == null){
            samosa = new Samosa();
        }
        return samosa;
    }
}

```
### Output:
```
705927765
ERROR!
Exception in thread "main" java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.DirectConstructorHandleAccessor.newInstance(DirectConstructorHandleAccessor.java:74)
	at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:502)
	at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:486)
	at Main.main(Main.java:12)
Caused by: java.lang.RuntimeException: You are trying to break singleton constructor
	at Samosa.<init>(Main.java:20)
	at java.base/jdk.internal.reflect.DirectConstructorHandleAccessor.newInstance(DirectConstructorHandleAccessor.java:62)
	... 3 more
```

- Solution 2: use enum:
```java
import java.lang.reflect.*;
class Main {
    public static void main(String[] args) throws Exception {

        Samosa samosa1 = Samosa.Instance;
        System.out.println(samosa1.hashCode());
        
        Constructor<Samosa> constructor = Samosa.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Samosa samosa2= constructor.newInstance();
        System.out.println(samosa2.hashCode());
    }
}
enum Samosa{
    Instance;
}
```
### Output:
```
705927765
ERROR!
Exception in thread "main" java.lang.NoSuchMethodException: Samosa.<init>()
	at java.base/java.lang.Class.getConstructor0(Class.java:3761)
	at java.base/java.lang.Class.getDeclaredConstructor(Class.java:2930)
	at Main.main(Main.java:9)
```



