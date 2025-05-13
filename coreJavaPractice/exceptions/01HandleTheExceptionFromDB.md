## Handle the Exception from db:
- In Java, when an exception occurs while **interacting with a database** (like using JDBC, JPA, or Hibernate), we can handle it using **try-catch blocks** and then **log or display a custom message** for better user understanding or debugging.

### Using JDBC Example:

```java
import java.sql.*;

public class DBExceptionExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";  // Use your DB
        String user = "root";
        String password = "wrongpassword";  // Intentionally wrong

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Database error occurred!");
            System.out.println("Error Message: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
        }
    }
}

```
### Output:
```
Database error occurred!
Error Message: Access denied for user 'root'@'localhost' (using password: YES)
SQL State: 28000
Error Code: 1045
```

### Using Custom Exception
- You can wrap SQL or persistence exceptions in a custom exception:
- **Custom Exception**:
```java
public class DatabaseAccessException extends RuntimeException {
    public DatabaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
```
- **Usage**:
```java
try {
    // DB logic here
} catch (SQLException e) {
    throw new DatabaseAccessException("Unable to connect to the database.", e);
}
```

### With Spring Boot + JPA
- In Spring Boot, exceptions are typically wrapped in DataAccessException.
```java
@Autowired
private UserRepository userRepository;

public void saveUser(User user) {
    try {
        userRepository.save(user);
    } catch (DataAccessException e) {
        System.out.println("Database error: " + e.getRootCause().getMessage());
    }
}

```

### 🔔 Tip: Logging Instead of Showing Full Errors
- For real-world apps, log errors and show user-friendly messages:
```java
logger.error("DB error", e);
throw new CustomAppException("Something went wrong, please try again later.");
```
*******************************************************************************************************
## ✅ What is DataAccessException in Java (Spring)?
- **DataAccessException is a runtime exception** provided by Spring Framework under the **org.springframework.dao package**. It is the **root of all data access exceptions** in Spring.

### 📌 Why use DataAccessException?
- Instead of forcing developers to catch **checked exceptions like SQLException**, Spring wraps them into **unchecked (runtime) exceptions** to:
  - Simplify error handling
  - Avoid **boilerplate try-catch** code
  - Provide a consistent exception hierarchy across different persistence technologies (JDBC, Hibernate, JPA, etc.)

### ✅ Hierarchy:
```
RuntimeException
   └── DataAccessException
         ├── CleanupFailureDataAccessException
         ├── ConcurrencyFailureException
         ├── DataIntegrityViolationException
         ├── DuplicateKeyException
         ├── EmptyResultDataAccessException
         └── ... (many more)
```
### 🔍 Example with Spring JPA:
```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void saveUser(User user) {
        try {
            userRepository.save(user);
        } catch (DataAccessException e) {
            System.out.println("Database access error: " + e.getMessage());
        }
    }
}
```

### 💡 Benefits of DataAccessException
|Benefit	|Explanation|
|---------|-----------|
|Unified Exception Hierarchy	|Same exceptions for JDBC, JPA, Hibernate, etc.|
|Unchecked	|No need to declare or catch explicitly|
|Specific subclasses	|You can handle specific issues like duplicates, empty rows|
|Framework Agnostic	|Works the same regardless of DB implementation|

### ✅ Conclusion
- DataAccessException is a runtime exception used by Spring to abstract database-related exceptions.
- It helps you write cleaner code without worrying about SQLException or specific DB errors.
- You can handle it globally or locally, depending on your application needs.






