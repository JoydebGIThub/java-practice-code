# What is volatile ?
volatile ensures that updates to a variable are always visible to all threads immediately,
preventing caching issues in a multithreaded environment.
## 🎯 Definition:
- volatile is a keyword in Java.
- It is used to mark a variable as "stored in main memory".
- Every read or write of a volatile variable is directly done on main memory,
not on the thread’s local cache.
## ✅ Use Case Example: 
You have multiple threads reading and writing a shared variable, and you want to ensure visibility across all threads.
```java
class Example {
    private volatile boolean flag = false;

    public void setFlagTrue() {
        flag = true;
    }

    public boolean isFlag() {
        return flag;
    }
}
```
"**Without volatile, a thread might not immediately see the updated value of flag.
With volatile, all threads immediately see the updated value.**"
