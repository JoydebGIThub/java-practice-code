# You have a array [1, 2, 4, 5, 6, 7]
## Add to index value and print the result
### Expected Output: 3, 6, 9, 11, 13
#### Solution no:1
```java
import java.util.*;
import java.util.stream.*;
class Main {
    public static void main(String[] args) {
        int[] a = {1, 2, 4, 5, 6, 7};
         IntStream.range(0, a.length - 1)
                .map(i -> a[i] + a[i + 1])
                .forEach(sum -> System.out.print(sum + ", "));
    
    }
}
```
#### Output:
```
3, 6, 9, 11, 13,
```
#### Solution no: 2
```java
import java.util.*;
import java.util.stream.*;
class Main {
    public static void main(String[] args) {
        int[] a = {1, 2, 4, 5, 6, 7};
        for(int i=0; i < a.length-1; i++){
            System.out.print(a[i]+a[i+1] + ",");
        }    
    }
}

```
#### Output:
```
3, 6, 9, 11, 13, 
```