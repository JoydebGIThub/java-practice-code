# Interview Therory Question:
## Q: Explain the use of ThreadPoolExecutor. How does it work internally?
### Why Use ThreadPoolExecutor?
Instead of creating and managing threads manually for every task, which can be resource-intensive and lead to performance issues, "ThreadPoolExecutor" provides a pool of reusable threads. This offers several advantages:
- Improved Performance: Thread creation and destruction are costly operations. Thread pools reuse existing threads, reducing this overhead and improving application responsiveness.
- Resource Management: Thread pools limit the number of active threads, preventing the system from being overwhelmed by too many threads consuming excessive memory and CPU resources.
- Better Control: "ThreadPoolExecutor" allows fine-grained control over thread lifecycle, queueing of tasks, and handling of rejected tasks.
- Simplified Code: It abstracts away the complexities of thread management, making it easier for developers to submit and execute tasks concurrently.
### Basic Usage:
You typically create a ThreadPoolExecutor using one of the static factory methods in the Executors class (though for more control, direct instantiation is recommended). You then submit Runnable or Callable tasks to the executor.
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) throws InterruptedException {
        // Using a fixed-size thread pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Runnable task = new WorkerTask("Task-" + (i + 1));
            executor.submit(task); // Submit the task for execution
        }

        // Initiates an orderly shutdown in which previously submitted
        // tasks are executed, but no new tasks will be accepted.
        executor.shutdown();

        try {
            // Wait for all tasks to complete or the timeout occurs
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("Some tasks did not finish within the timeout.");
                executor.shutdownNow(); // Attempt to stop all actively executing tasks
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

class WorkerTask implements Runnable {
    private String taskName;

    public WorkerTask(String name) {
        this.taskName = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started " + taskName);
        try {
            TimeUnit.SECONDS.sleep(2); // Simulate some work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " finished " + taskName);
    }
}
```
### How ThreadPoolExecutor Works Internally:
The ThreadPoolExecutor manages a pool of threads and a work queue to handle submitted tasks. Here's a breakdown of its internal workings:
1. Core Pool Size: This is the initial number of threads that are kept alive in the pool, even if they are idle. The executor tries to maintain at least this many threads.
2. Maximum Pool Size: This is the maximum number of threads that can be created in the pool. If the queue is full and more tasks are submitted, the executor can create new threads up to this limit.
3. Keep-Alive Time: When the number of threads in the pool exceeds the core pool size, idle threads are kept alive for this amount of time. If they remain idle beyond this time, they are terminated.
4. Work Queue: This is a queue that holds the tasks submitted by the submit() method that are waiting to be executed. Different types of queues can be used:
   - SynchronousQueue: A queue with no internal capacity. A task will be handed off to a thread immediately, or it will be rejected if no thread is available. This policy favors creating more threads up to the maximum pool size.
   - LinkedBlockingQueue: An unbounded queue (in practice, limited by system memory). If the core pool size is reached, new tasks will be queued indefinitely. This policy favors keeping the number of threads at the core size.
   - ArrayBlockingQueue: A bounded queue with a fixed capacity. Once the core pool size is reached and the queue is full, the executor will create new threads up to the maximum pool size. If the maximum pool size is also reached and the queue is still full, the task will be rejected.
5. Thread Factory: This is an object that the executor uses to create new threads. You can customize the thread factory to set thread names, priority, daemon status, etc. The default thread factory creates non-daemon threads with normal priority.
6. Rejected Execution Handler: This handler is invoked when the executor cannot accept a task for execution (e.g., when the maximum pool size has been reached and the queue is full). Several built-in handlers are available:
   - AbortPolicy (default): Throws a RejectedExecutionException.
   - CallerRunsPolicy: Executes the task in the thread that submitted it. This can help to slow down the rate at which new tasks are submitted.
   - DiscardPolicy: Silently discards the rejected task.
   - DiscardOldestPolicy: Discards the oldest unhandled task in the queue and then tries to execute the current task.
#### Task Submission and Execution Flow:
When you submit a task to the ThreadPoolExecutor using executor.submit(task):
1. If the number of running threads is less than the core pool size: The executor creates a new thread and executes the task.
2. If the number of running threads is equal to or greater than the core pool size: The task is added to the work queue.
3. If the work queue is full:
   - If the number of running threads is less than the maximum pool size: The executor creates a new thread and executes the task.
   - If the number of running threads is equal to or greater than the maximum pool size: The task is rejected, and the configured RejectedExecutionHandler is invoked.
4. Idle Thread Management: If the number of threads in the pool exceeds the core pool size and a thread remains idle for longer than the keep-alive time, it will be terminated to conserve resources.
#### Direct Instantiation for More Control:
While Executors provides convenient factory methods, directly instantiating ThreadPoolExecutor gives you more control over its configuration:
```java
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;

public class CustomThreadPoolExecutor {
    public static void main(String[] args) {
        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepAliveTime = 60; // seconds
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler rejectedHandler = new ThreadPoolExecutor.CallerRunsPolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue,
                threadFactory,
                rejectedHandler
        );

        for (int i = 0; i < 20; i++) {
            executor.submit(new WorkerTask("Custom-Task-" + (i + 1)));
        }

        executor.shutdown();
    }
}
```

Think of a busy government service center (like a passport office).

- Core Pool Size: The number of permanently staffed counters that are always open.
- Maximum Pool Size: The total number of counters that can be opened during peak hours.
- Keep-Alive Time: If some counters become idle during off-peak hours, they might be temporarily closed (threads terminated) after a certain period of inactivity.
- Work Queue: The waiting area where people (tasks) line up if all counters are busy. The type and size of the waiting area (queue) affect how people are managed.
- Rejected Execution Handler: What happens if the waiting area is full and more people arrive? They might be turned away (AbortPolicy), asked to wait in the current line (CallerRunsPolicy - the person who brought them in has to handle them), or simply ignored (DiscardPolicy).
