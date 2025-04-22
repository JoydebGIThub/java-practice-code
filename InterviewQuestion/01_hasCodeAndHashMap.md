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
