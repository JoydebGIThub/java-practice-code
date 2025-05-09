/*
What is Serializable?
Ans: In Java, Serializable is a marker interface (i.e., an interface with no methods) used to indicate that a class can be serialized
— that is, its objects can be converted into a byte stream so they can be:
1. Saved to a file or database,
2. Transmitted over a network,
3. Or cached in memory for later retrieval.
*/

package coreJavaPractice.serializable;

import java.io.*;

// Step 1: Create a Serializable class
class User implements Serializable {
    String username;
    transient String password; // transient: won't be serialized

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class SerializationDemo {
    public static void main(String[] args) throws Exception {

        // Step 2: Create an object
        User user = new User("john_doe", "secret123");

        // Step 3: Serialize the object
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.txt"));
        out.writeObject(user);
        out.close();
        System.out.println("✅ Object serialized successfully!");

        // Step 4: Deserialize the object
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("user.txt"));
        User deserializedUser = (User) in.readObject();
        in.close();

        // Step 5: Print the deserialized data
        System.out.println("\n🎯 After Deserialization:");
        System.out.println("Username: " + deserializedUser.username);
        System.out.println("Password: " + deserializedUser.password); // Password will be null
    }
}
