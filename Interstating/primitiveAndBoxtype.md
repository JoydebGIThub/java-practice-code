## difference between primitive types and boxed types (objects) in Java
```java
int a = 10;
a = 20;
```
#### 💡 What Happens Internally?
1. int a = 10;
   - Memory is allocated for variable a (typically 4 bytes).
   - Value 10 is stored in that memory.
2. a = 20;
   - The value 20 replaces the existing value 10 in the same memory location.
   - Java does not create a new memory location here — it just overwrites the existing value.
```java
Integer b = 1000;
b = 200;
```
#### ✅ What Happens Internally?
1. Integer b = 1000;
   - 1000 is autoboxed to an Integer object.
   - A new Integer object is created on the heap.
   - Variable b holds a reference to this object.
2. b = 200;
   - Another Integer object is created (again autoboxed).
   - Now, b points to the new object (which holds the value 200).
   - The old object (1000) is not overridden — it's still in memory and may be garbage collected if not referenced elsewhere.

## 🔥 Bonus: What if the values were small (like b = 127)?
- **Java caches Integer values** from **-128 to 127** using the **Integer cache**. So:
```java
Integer b1 = 127;
Integer b2 = 127;
System.out.println(b1 == b2);  // true (same cached object)
```

- But
```java
Integer b1 = 1000;
Integer b2 = 1000;
System.out.println(b1 == b2);  // false (different objects)

```

