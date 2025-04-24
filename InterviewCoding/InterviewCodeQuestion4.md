# Coding question:
## Find the maximum
### Solution no: 1
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        int[] arr= {1, 20, 5, 10, 12};
        List<Integer> l= new ArrayList<>();
        for(int i=0; i<arr.length;i++){
            l.add(arr[i]);
        }
        int max= l.stream().max(Integer::compareTo).get();
        System.out.println(max);
        
        
    }
}

```
### Solution no: 2
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        int[] arr= {200, 20, 5, 10, 100};
        int max=0;
        for(int i=0; i<arr.length-1;i++){
            if(arr[i]<arr[i+1]){
                max= max>arr[i+1]? max: arr[i+1];
            }else if(arr[i]>arr[i+1]){
                max= max>arr[i]? max: arr[i];
            }
        }
        System.out.println(max);
    }
}

```

### Solution no: 3
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        int[] arr= {1, 20, 5, 10, 12};
        List<Integer> l= new ArrayList<>();
        for(int i=0; i<arr.length;i++){
            l.add(arr[i]);
        }
        l.stream().max(Integer::compareTo).ifPresent(n-> System.out.print(n));
    }
}

```
### Solution no: 4
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        int[] a= {1, 20, 5, 10, 12};
        List<Integer> l= Arrays.asList(Arrays.stream(a).boxed().toArray(Integer[]::new));
        l.stream()
            .max(Integer::compareTo)
            .ifPresent(n-> System.out.print(n));
    }
}

```
#### Explanation:
1. Arrays.asList() and the ArrayList constructor
2. Arrays.stream(a): This creates an "IntStream" from the integer array "a".
3. .boxed(): This converts the "IntStream" to a "Stream<Integer>", wrapping each int in an Integer object.
4. .toArray(Integer[]::new): This collects the elements of the stream into a new Integer[] array.
### Solution no:5
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        int[] a= {1, 20, 5, 10, 12};
        List<Integer> l= Arrays.stream(a).boxed().toList();
        l.stream()
            .max(Integer::compareTo)
            .ifPresent(n-> System.out.print(n));
    }
}
```
#### Explanation:
1. .toList(): This collects the elements of the stream into an immutable List<Integer>.