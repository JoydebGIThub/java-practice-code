## hashCode() method
- hashCode() is a method defined in the 🔤Object class in java.
- Every Java Object has a hashCode, which is an ◾int value.
- It is used to ◼️uniquely identify objects during operations like storing them in hash-based collections such as HashMap, HashSet and Hashtable.
- 🪕It helps in 🏃 faster searching, inserting and retrival in hash-based data structures.
```java
String s1="Hello";
System.out.println(s1.hashCode());
```
```
Each time you run this, the same string gives the same hash code (because String overrides hashCode()) properly.
We only can get the hashCode() for non-primitive datatype.
```
```java
int[] i={1, 2, 4};
System.out.println("int hashCode: "+i.hashCode());//692404036
```
```java
int i=4;
System.out.println("int hashCode: "+i.hashCode()); //error: int cannot be dereferenced
```
## HashMap in Java
```
A HashMap is a data structure that stores 🔐key-value pairs. It uses a ◾hash table for implementation and is part of the 📦java.util package.
```
1. When we put the key-value pair:
   - Java calculates the hashCode() of the 🔑 key.
   - It uses that hash to determaine where to 🏬store the value in 🧠memory (called a 🪣bucket).
2. When you get a value:
   - It 📲 calculates the hash of the 🔑 key again.
   - It goes to that 🪣bucket and ✔️checks the 🔑 key using equals() to retrive the value.
```java
HashMap<String, String> map=new HashMap<>();
map.put("name","Joydeb");
map.put("city","Kolkata");
System.out.println(map.get("name"));//Joydeb
```
### 🧠 hashCode+equals = HashMap:
- hashCode() helps find the 🪣bucket where the 🔑 key might be.
- equals() ✔️checks whether the exact key is found within the bucket.
```
If we override one, we mush override both, otherwise the HashMap may not work correctly
```
### 🔑 Tips:
- A good hashCode should be fast and distribute keys evenly.
- Collisions (when different key have the same hash) are handled by ⛓️ chaining or 📖 open addressing internally.

## Hashtable (Legacy class, key-value pair):
- A 🔐key-value based collection like HashMap.
- 🧵Thread-safe(synchronized) - good for multi-threaded environments.
- 🪹 No null keys or null value allowed.
- Slower than HashMap due to synchronization.
- Part of legacy collection classes.
```java
Hashtable<Integer, String> table= new Hashtable<>();
table.put(1, "Apple");
table.put(2, "Banana");

//table.put(null, "Test");// throws NullPointerException
System.out.println(table.get(1));
```
```java
 HashMap<Integer, String> h= new HashMap<>();
h.put(1, "Joydeb");
h.put(null,"kal");
h.put(null,"Joydeb");//override
h.put(2,"null");
h.put(3,"null");
System.out.print(h.get(null)); //kal
```

## HashSet (Collection of unique elements):
- Stores only 👽unique elements - no duplicates.🧔
- Implements Set interface.
- Internally uses a HashMap (keys as elements, values as a dummy constant).
- Allows one 🪹 null element.
- Not synchronized (not thread-safe).
- Unordered - does not maintain insertion order.
```java
HashSet<String> set= new HashSet<>();
set.add("Java");
set.add("Python");
set.add("Java"); // Duplicate won't be added
System.out.println(set);
```
