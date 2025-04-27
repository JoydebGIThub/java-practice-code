# Set Interface in Java
The Set interface in Java is part of the java.util package and represents a collection that does not allow duplicate elements. Sets are also unordered, meaning they don’t guarantee any specific order of elements. The Set interface extends the Collection interface and is primarily used to model mathematical sets where uniqueness is the key feature.

## Common Implementations of Set Interface in Java:
1. HashSet
   -
   - Description:
     - Implements the "**Set**" interface using a "**hash table**" (backed by a HashMap).
     - It is the most commonly used Set implementation and does "**not guarantee the order**" of elements.
   - Key Features:
     - No Duplicates: Ensures no duplicate elements.
     - Unordered: Elements are stored in no particular order.
     - Constant time performance for basic operations (add, remove, contains), assuming the hash function disperses the elements properly.
     -  We can add null as a value
```java
Set<String> set = new HashSet<>();
set.add("Apple");
set.add("Banana");
set.add("Apple");  // Duplicate, will not be added
System.out.println(set);  // Output: [Apple, Banana] (order may vary)

```
2. LinkedHashSet
   -
   - Description:
     - Extends HashSet but "**maintains insertion order**". It uses a doubly linked list to maintain the order in which elements are inserted into the set.
   - Key Features:
     - Order-preserving: Maintains the order of insertion (the order in which elements were added).
     - No Duplicates: Ensures uniqueness of elements.
     - Slightly slower than HashSet because of the additional overhead of maintaining the order.
     - We can enter null as a value
```java
Set<String> set = new LinkedHashSet<>();
set.add("Apple");
set.add("Banana");
set.add("Apple");  // Duplicate, will not be added
System.out.println(set);  // Output: [Apple, Banana] (insertion order preserved)


Set<Integer> set = new LinkedHashSet<>();
set.add(1);
set.add(2);
set.add(1);  // Duplicate, will not be added
set.add(null);
System.out.println(set);  // Output: [1, 2, null] (insertion order preserved)
```
3. TreeSet
   -
   - Description:
     - Implements Set using a red-black tree structure, which is a type of self-balancing binary search tree.
     - Sorted: Elements are stored in ascending order by default, or according to a provided Comparator.
   - Key Features:
     - Sorted Order: Maintains a natural ordering of elements or the ordering defined by a comparator.
     - No Duplicates: Ensures uniqueness of elements.
     - Provides log(n) time complexity for basic operations (add, remove, contains).
     - we can't add null as a value.
```java
Set<Integer> set = new TreeSet<>();
set.add(3);
set.add(1);
set.add(2);
set.add(1);  // Duplicate, will not be added
//set.add(null);// Exception in thread "main" java.lang.NullPointerException
System.out.println(set);  // Output: [1, 2, 3] (sorted in natural order)
```


