# Remove duplicates from an unsorted array without using additional data structure.
## Solution no: 1
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        int[] a= {1, 2, 4, 5, 3, 2, 5, 6};
        System.out.println(Arrays
        .stream(a)
        .boxed()
        .distinct()
        .toList());  
    }
}
```
### Output:
```
[1, 2, 4, 5, 3, 6]
```
## Solution no: 2
```java
public class RemoveDuplicatesInPlace {

    public static int removeDuplicates(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr == null ? 0 : arr.length;
        }

        int uniqueCount = 0; // Index to track the position of the next unique element

        for (int i = 0; i < arr.length; i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (arr[i] == arr[j]) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                arr[uniqueCount++] = arr[i];
                    // System.out.print(arr[i]+ ", ");
            }
        }

        return uniqueCount; // Returns the new length of the array with duplicates removed
    }

    public static void main(String[] args) {
        int[] numbers1 = {1, 2, 2, 3, 4, 4, 4, 5};
        int newLength1 = removeDuplicates(numbers1);
        System.out.println("Array after removing duplicates:");
        for (int i = 0; i < newLength1; i++) {
            System.out.print(numbers1[i] + " ");
        }
        System.out.println(); // Output: 1 2 3 4 5

        int[] numbers2 = {5, 2, 5, 1, 3, 2};
        int newLength2 = removeDuplicates(numbers2);
        System.out.println("Array after removing duplicates:");
        for (int i = 0; i < newLength2; i++) {
            System.out.print(numbers2[i] + " ");
        }
        System.out.println(); // Output (order might vary): 5 2 1 3

        int[] numbers3 = {1, 1, 1, 1, 1};
        int newLength3 = removeDuplicates(numbers3);
        System.out.println("Array after removing duplicates:");
        for (int i = 0; i < newLength3; i++) {
            System.out.print(numbers3[i] + " ");
        }
        System.out.println(); // Output: 1

        int[] numbers4 = {};
        int newLength4 = removeDuplicates(numbers4);
        System.out.println("Array after removing duplicates (empty): " + newLength4); // Output: 0
    }
}
```
### Output:
```
Array after removing duplicates:
1 2 3 4 5 
Array after removing duplicates:
5 2 1 3 
Array after removing duplicates:
1 
Array after removing duplicates (empty): 0
```
