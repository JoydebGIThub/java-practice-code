# InterView questions:
## Q: What are some differences between ReentrantLock and synchronized?
### Core Functionality: Locking Mechanism
- synchronized: This is a keyword built into the Java language. It provides a basic, implicit locking mechanism. When you use synchronized on a method or a block of code, Java automatically handles the acquisition and release of the lock associated with the object (for instance methods/blocks) or the class (for static methods/blocks). Think of it like a built-in, simple lock on a door.
- ReentrantLock: This is a class in the java.util.concurrent.locks package that implements the Lock interface. It provides an explicit locking mechanism. You need to explicitly call lock() to acquire the lock and unlock() to release it, often within a try-finally block to ensure the lock is always released. Think of it like a separate, more sophisticated padlock you need to manually apply and remove.
### Analogy to a Single-Lane Bridge:
- synchronized: Imagine a traffic light at a single-lane bridge. When the light is green, one car goes. The system manages the light automatically. If your car arrives when the light is red, you simply wait. There's no way to say, "I'll wait for only 5 seconds," or for the system to prioritize cars that have been waiting longer.
- ReentrantLock: Imagine a bridge with a human guard managing access.
  - Fairness: The guard can choose to let cars through in the order they arrived (fairness).
  - Try-Lock: You can ask the guard if you can cross immediately. If not, you can choose to wait for a specific time or not wait at all.
  - Interruptibility: If there's an emergency, someone can tell the waiting cars to make way.
  - Multiple Conditions: The guard might have different signals for different types of vehicles or situations (like a separate waiting area for trucks).
