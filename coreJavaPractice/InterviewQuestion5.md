# Interview Therory Question:
## Q: What is the difference between ArrayList, LinkedList, and Vector?
```
The "ArrayList", "LinkedList", and "Vector" classes in Java all implement the "List" interface, meaning they represent "ordered colections of elements" that can contain "duplicates".
However, they differ significantly in their underlying data structures and how they perform various operations, as well as their synchronization behavior. Here's a breakdown of their key differences:
```
1. Underlying Data Structure:
   - ArrayList: Internally uses a "dynamically resizable array". This means elements are stored in "contiguous memory locations", similar to a regular array. When the array becomes full, a new, larger array is created, and the elements are copied over.
   - LinkedList: Internally uses a "doubly linked list". Each element (node) in the list stores the actual data and pointers (references) to the "previous" and "next" elements in the sequence.
   - Vector: Also internally uses a "dynamically resizable array", very similar to "ArrayList".
2. Performance Characteristics:
   - ArrayList:
     - Accessing elements by index (get(index), set(index)): O(1) (constant time) because of direct access to array elements.
     - Adding or removing elements at the end: O(1) on average (amortized constant time). Resizing might occasionally take O(n) time.
     - Adding or removing at the begining or in the middle: O(n) (linear time) because elements need to be shifted to make space or fill the gap.
    - LinkedList:
      - Accessing elements by index(get(index), set(index)): O(n) (linear time) because it needs to traverse the list from the beginning(or end, if closer) to reach the desired index.
      - Adding or removing element at the beginning or end: O(1) (constant time) as it only involves updating the pointers of the adjacent nodes.
      - Adding or removing elements in the middle(if the position is already known): O(1)(after finding the position, which takes O(n) in the worst case)
    - Vector: Performance characteristics are generally similar to "ArrayList" for most operations due to the underlying dynamic array. However, the synchronization overhead can make it slower in multithreaded scenarios compared to a non-synchronized "ArrayList".
3. Synchronization:
   - ArrayList: Not synchronized (not thread-safe). Multiple thread can access and modify an ArrayList concurrently, which can lead to data corruption if not properly managed externally.
   - LinkedList: Not synchronized (not thread-safe) for the same reasons as "ArrayList".
   - Vector: Synchronized (thread-safe). All its public methods are synchronized, meaning that only one thread can access a "Vector" instance at a time. This prevents "race conditions" in multithreaded environments but introduces performance overhead due to the locking mechanism.
