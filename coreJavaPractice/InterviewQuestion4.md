# Interview Therory Question:
## Q: What is autoboxing 🧰 and unboxing 📤 in Java?
### Autoboxing:

- Autoboxing 🧰 is the automatic conversion that the Java compiler makes between the "primitive types" and their corresponding "object wrapper classes".
  - int to Integer
  - double to Double
  - boolean to Boolean
  - and so on...

The compiler applies autoboxing when a primitive value is:
- Passed as an argument to a method that expects an object of the corresponding wrapper class.
- Assigned to a variable of the corresponding wrapper class.

```java
int primitiveInt = 10;
Integer wrapperInteger = primitiveInt; // Autobixing: int is automatically converted to Integer

double primitiveDouble = 3.14;
Double wrapperDouble = primitiveDouble; // Autoboxing: double is automatically converted to Double

List<Integer> integerList = new ArrayList<>();
integerList.add(5); //Autoboxing: int 5 is automatically converted to an Integer object  
```
### Unboxing:
- Unboxing 📤 is the reverse process. It's the automatic conversion that the java compiler makes from an "object of a wrapper class" to its corresponding "primitive type"
  - Integer to int
  - Double to double
  - Boolean to boolean
  - and so on...

The compiler applies unboxing 📤 when an object of a wrapper class is
- Passed as an argument to a method that expects a value of the corresponding primitive type.
- Assigned to a variable of the corresponding primitive type.
- Used in an operation where a primitive value is expected (arithmetic operations, comparisons)

```java
Integer i= 20;
int j=i;

Double a= Double.valueOf(2.71);
double b= a;

Integer num1= 5;
Integer num2= 6;
int sum = num1+num2; // Unboxing: Integer objects are unboxed to int for addition
```
***
***
## Q: How is HashMap implemented internally? How does it handle collisions?
### Internal Implementation: The Core Ideas
- At its heart 🫀, HashMap 🗺️ in java is based on the principle of a "**hash table**". This means it uses a "**hashing function**" to compute an index(a "bucket 🪣") for each key 🗝️, allowing for (ideally) constant-time ⏲️ average complexity for basic operations like "get", "put", "remove", and "containsKey". 

Here's a simplified view of its internal structure:
1. **An Array of Buckets**: HashMap 🗺️ internally maintains an array called the "**bucket 🪣 array**" (or sometimes referred to as the "table"). Each element of this array is a "**bucket🪣**", and each bucket🪣 can **hold a collection of key-value pairs**.
2. **Entry (or Node) Objects**: The key-value 🔑 pairs are stored in objects of an inner class called "Entry" (before java 8) or "Node" (from java 8). These objects contain:
   - The "key" 🗝️ itself.
   - The "Value" associated with the key 🗝️.
   - A reference to the next "Entry/Node" in the same bucket🪣 (used for collision handling, as we'll see).
   - A hash value that is stored for efficiency.
3. **Hashing Function**: When you put a key-value🗝️ pair into the "**HashMap 🗺️**", the following happens:
   - The "**hashCode()**" method of the key🔑 object is called to generate an integer hash code.
   - This hash code is then further processed by the "**HashMap🗺️**"'s internal hashing function (which often involves bitwise operations) to distribute the keys 🗝️🗝️ more uniformly across the buckets🪣🪣 and handle potential poor hash code implementations
   - The result of this processing is used to calculate 📲 the "**index**" of the bucket🪣 where this key-value🗝️ pair should be stored. This index is typically obtained by taking the modulo of the processed hash code with the current size of the bucket array(index = hashCode % arraySize)

### Handiling Collision: When Multiple Keys Map to the Same Bucket:
- A "**collision**" occurs when two or more different keys 🗝️produce the same hash code(after the "HashMap"s internal processing) and thus map to the same bucket in the array. HashMap🗺️ employs different strategies to handle these "**collisions**", evolving significantly from earlier Java version to Java 8 and beyond.

#### Pre-Java 8: Separate Chaining ⛓️ with Linked Lists:
- In older jave verisons(before java 8), HashMap🗺️ primarily used "**separate chaining**"⛓️ with "**linked lists**" to resolve collisions:
1. Linked List per Bucket: If multiple key-value 🗝️ pairs map to the same bucket 🪣, they are stored in a linked list. The "next" reference is each "Entry" object points to the next entry in the list within that bucket🪣.
2. Insetion at the Head: New entries were typically inserted at the head of the linked list in the colliding bucket🪣.
3. Searching: When you try to "get" a value for a given key, "HashMap🗺️" calculates the bucket🪣 index. It then iterates through the linked list in that bucket🪣, comparing the keys using the "equals()" method to find the desired entry.

The Problem with Long Linked lists, if many keys happen to have the same (or similar) hash code, a single bucket🪣 could end up with a very long linked list. Searching through a long linked list has a time complexity ⏲️ of O(n) in the worst case (where n is the number of entries in the list), degrading the performance of "HashMap" from its ideal O(1) avarage time complexity⏲️.

#### Java 8 and Later: Separate Chaining with self-balancing binary search trees.

- Java 8 introduced a significant optimization to handle collision more efficiently:
1. Linked List Threshold: When the number of entries in a single bucket exceeds a certain threshold (default is 8), and if the table's capacity is at least 64, the linked list in that bucket is "**converted into a balanced binary search tree(specifically, a TreeNode🌴 which is a subclass of Node)**".
2. Tree Structure: These trees maintain a "**sorted order**" based on the hash codes (and then key 🗝️ comparison if hash codes are equal), allowing for faster searching ((Ologn) in the worst case) compared to a linked list.
3. Reversion to Linked List: If the number of entries in a tree-based bucket falls below another threshold(default 6), the tree is converted back into a linked list. This is because for a small number of elements, the overhead of maintaining a tree structure might outweigh the benefits of faster searching.
4. Insertion in Trees: New nodes are inserted into the tree while maintaining its balance structure.
