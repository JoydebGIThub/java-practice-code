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