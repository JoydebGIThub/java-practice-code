# ✅ Serializable and Transient in Java
## 🎯 Serializable
- Serializable is a marker interface (no methods inside) in the java.io package.
- It is used to mark a class so that its objects can be converted into a byte stream (serialization) — for saving to a file, sending over network, etc.
```java
import java.io.Serializable;

class Student implements Serializable {
    int id;
    String name;
}
```
### ✅ Why Serializable? Serialization is needed to:
- Save object state permanently (file, DB, etc.)
- Send objects over network (RMI, sockets, etc.)
#### ✅ Important: 
If you try to serialize an object of a class that does not implement Serializable, JVM will throw a NotSerializableException.
## 🎯 Transient
- transient is a keyword used to mark a field that should NOT be serialized.
- When an object is serialized, transient fields are skipped — they are not saved in the byte stream.
```java
import java.io.Serializable;

class User implements Serializable {
    String username;
    transient String password; // Will not be serialized
}
```
"**When you serialize and deserialize User, the password will be null (default value) after deserialization.**"










