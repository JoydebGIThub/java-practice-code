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
****
## Q: What is the difference between Runnable 🏃and Callable 🤙?
```
Both "Runnable" and "Callable" are interfaces that represent tasks that can be executed by threads
```
1. Return Value:
   - Runnable 🏃: The "run()" method defined in the Runnable🏃 interface "does not return any value" (its return type is "void"). It's primarily used to execute a sequence of actions without needing a result.
     ```java
     public interface Runnable{
         public abstract void run();
     }
     ```
   - Callable🤙: The "call()" method defined in the Callable🤙 interface "can return a result". The return type in parameterized(V), allowing you to specify the type of the value returned by the task.
     ```java
     public interface Callable<V>{
         V call() throws Exception;
     }
     ```
2. Exception Handling:
   - Runnable🏃: The "run()" method "cannot" throw checked exceptions. If a checked exception occurs within the "run()" method, it mush be caugth and handled within the method itself.
   - Callable🤙: The "call()" method "can throw checked exceptions". This allows the task to signal potential errors or exceptional conditions to the calling code. The "Future" object (which is used to get the result of a Callable) can then be used to handle these exceptions.
3. Usage with "ExecutorService":
   - Runnable🏃: You can submit a Runnable🏃 task to an "ExecutorService" using the "execute()" method. This method doesn't provide a way to get the result of the task or track its completion in a direct, value-returning way.
     ```java
     ExecutorService executor = Executors.newSingleThreadExecutor();
     executor.execute(new MyRunnable()); // No direct way to get a result
     executor.shutdown();
     ```
   - Callable🤙: You submit a Callable🤙 task to an ExecutorService using "submit()" method. The "submit()" method returns a "Future" object. This "Future" object represents the result of the asynchronous computation. You can use the "Future" to:
     - Check if the task is complete(isDone()).
     - Wait for the task to complete and retrieve its result(get()), which can block until the result is available.
     - Retrieve the result with a timeout(get(long timeout, TimeUnit unit)).
     - Cancel the task(cancel(boolean mayInterruptIfRunning)).
     - Check if the task was cancelled (isCancelled()).
       ```java
       ExecutorService executor= Executors.newSingleThreadExecutor();
       Future<String> futureResult= executor.submit(new MyCallable());
       try{
            String result= futureResult.get();// Blocks until the result is available.
            System.out.println("Result: "+ result);
       }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
       }
       executor.shutdown();
       ```
4. Purpose:
   - Runnable🏃: Primarily used for executing independent units of work that don't need to return a specific value. Think of tasks like updating a UI, performing background processing without needing a direct outcome, or simple event handling.
   - Callable🤙: Designed for tasks that perform a computation and need to return a result. This is useful for operations like fetching data performing calculations, or any asynchronous task where you need to obtain the outcome.
5. Lambda Expressions and Functional Interfaces:
   - 
