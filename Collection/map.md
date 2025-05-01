# Map Interface in Java
The Map interface in Java is part of the java.util package and represents a collection of "**key-value pairs**". Each key is unique and maps to a specific value, meaning that no two entries in the map can have the same key, but the values can be duplicated. Map is not a true Collection, but rather a structure used for storing associative arrays or dictionaries.
## Common Implementations of Map Interface in Java:
1. HashMap
   -
   - Description:
     - Implements the Map interface using a hash table (backed by HashMap).
     - Allows "**null values and null keys**".
     - Does not guarantee any specific order of the elements.
   - Key Features:
     - No duplicates: Keys are unique.
     - Unordered: No predictable order of elements.
     - Constant time complexity (O(1)) for basic operations like put(), get(), and remove(), assuming a good hash function.
```java
Map<String, String> map = new HashMap<>();
map.put("A", "Apple");
map.put("B", "Banana");
map.put("C", "Cherry");
map.put("C", "Hello"); // Override the value
map.put("null", "Joydeb"); //null key
map.put("D", "null"); //nul value
System.out.println(map);  // Output: {A=Apple, B=Banana, C=Hello, null=Joydeb, D=null} (order may vary)
````
2. LinkedHashMap
   -
   - Description:
     - Extends HashMap but maintains insertion order using a doubly-linked list.
     - Allows "**null values and null keys**".
   - Key Features:
     - Order-preserving: Maintains the order of insertion (the order in which entries were added).
     - No duplicates: Keys are unique.
     - Slightly slower than HashMap due to the additional overhead of maintaining the insertion order.
```java
Map<String, String> map = new LinkedHashMap<>();
map.put("A", "Apple");
map.put("B", "Banana");
map.put("C", "Cherry");
System.out.println(map);  // Output: {A=Apple, B=Banana, C=Cherry} (insertion order preserved)

```
3. TreeMap
   -
   - Description:
     - Implements the Map interface using a red-black tree, which is a self-balancing binary search tree.
     - Keys are sorted in ascending order by default or according to a provided Comparator.
     - Only allow "**null value**" but "**null key are not allowed**" 
   - Key Features:
     - Sorted: Keys are ordered based on natural ordering or a custom comparator.
     - No duplicates: Keys are unique.
     - Operations like put(), get(), remove() take O(log(n)) time due to the tree structure.
```java
Map<Integer, String> map = new TreeMap<>();
map.put(3, "Three");
map.put(1, "One");
map.put(2, "Two");
System.out.println(map);  // Output: {1=One, 2=Two, 3=Three} (sorted by key)


Map<Integer, Integer> map = new TreeMap<>();
map.put(3, 3);
map.put(1, 1);
map.put(2, 2);
map.put(4, null);
System.out.println(map);  // Output: {1=1, 2=2, 3=3, 4=null} (sorted by key)
```    
4. Hashtable
   -
   - Description:
     - An older implementation of Map based on a hash table (similar to HashMap).
     - It is synchronized, meaning it is thread-safe but has a performance overhead due to synchronization.
   - Key Features:
     - Thread-safe: Useful in multi-threaded environments.
     - No null values: Does "**not allow null keys or null values**".
     - Operations like put() and get() take O(1) time on average.
```java
Map<String, String> map = new Hashtable<>();
map.put("A", "Apple");
map.put("B", "Banana");
// map.put(null, "Null");  // Throws NullPointerException
System.out.println(map);  // Output: {A=Apple, B=Banana}
````
***************************************************************************************************************
## Map methods:
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        Map<Integer, String> maps = new HashMap<>();
        maps.put(1, "Joydeb");
        maps.put(2, "Joy");
        maps.put(3, "deb");
        System.out.println(maps);//{1=Joydeb, 2=Joy, 3=deb}
        System.out.println(maps.get(1));//Joydeb
        System.out.println(maps.keySet());//[1, 2, 3]
        maps.forEach((key, value)->{
           System.out.print(key+ ", ");  // 1, 2, 3,
        });
    }
}
```





