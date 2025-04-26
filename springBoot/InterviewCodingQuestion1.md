# Write a REST API in Spring Boot that returns "Hello, World!"
```java
@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
```
*****************
# Write a Spring Boot service that saves a user to a database (use JPA Repository).

## User.java
```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
}
```

## UserRepository.java
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}

```
## UserService.java
```java
@Service
public class UserService{
  @Autowired
  private UserRepository repo;

  public User saveUser(User user){
    return repo.save(user);
  }
}
```

## UserController.java
```java
@RestController
public class UserController{
  @Autowired
  private UserService service;

  @PostMapping("/user")
  public User createUser(@RequestBody User user) {
        return service.saveUser(user);
  }
}
```
************
# Create a global exception handler that returns a custom error response when any exception occurs.
## CustomErrorResponse.java
```java
public class CustomErrorResponse {
    private String message;
    private int status;
    private long timestamp;

    // getters and setters
}
```
## GlobalExceptionHandler.java
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleAllExceptions(Exception ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimestamp(System.currentTimeMillis());
        
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```
**********************
# How do you configure multiple data sources?
## DataSourceConfig.java
```java
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // Configuration for the primary database (like your main LEGO set)
    @Primary // Marks this as the default DataSource if no specific one is requested
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/main_db") // The instructions for the main LEGO box
                .driverClassName("com.mysql.cj.jdbc.Driver") // The type of connector for the main LEGO bricks
                .username("user") // Your main builder account
                .password("password") // Your main builder password
                .build(); // Assembling the connection setup
    }

    // Configuration for a secondary database (like a smaller, separate LEGO set)
    @Bean(name = "secondaryDataSource")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/backup_db") // Instructions for the backup LEGO box
                .driverClassName("org.postgresql.Driver") // The type of connector for the backup LEGO bricks
                .username("backup_user") // Your backup builder account
                .password("backup_password") // Your backup builder password
                .build(); // Assembling the backup connection setup
    }

    // You might also define TransactionManager beans if you need transactional control
    // For simplicity, we're skipping that here.
}
```
### Explanation:
- DataSourceBuilder.create(): This is like getting the instructions out of a specific LEGO box.

- .url("jdbc:mysql://localhost:3306/main_db")and.url("jdbc:postgresql://localhost:5432/backup_db")`: These are the specific addresses (URLs) for each database, like the location of your "Main Set" and "Backup Set" LEGO boxes.

- .driverClassName("com.mysql.cj.jdbc.Driver")and.driverClassName("org.postgresql.Driver")`: These tell Java what type of "connector" or "adapter" is needed to talk to each specific database, like knowing whether you need a specific type of tool to connect certain LEGO bricks.

- .username("user")and.username("backup_user")`: These are the usernames you use to access each database, like your unique builder accounts for each LEGO set.

- .password("password")and.password("backup_password")`: These are the passwords for each database, like the secret codes to open each LEGO box.

- **.build():** This is like the final step of assembling the connection setup based on the instructions. It creates the actualDataSource` object that your application can use to interact with the database.
## UserRepository:
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class UserRepository {

    private final DataSource primaryDataSource;
    private final DataSource secondaryDataSource;

    @Autowired
    public UserRepository(@Qualifier("primaryDataSource") DataSource primaryDataSource,
                          @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
        this.primaryDataSource = primaryDataSource;
        this.secondaryDataSource = secondaryDataSource;
    }

    public Connection getConnectionToPrimary() throws SQLException {
        return primaryDataSource.getConnection(); // Using the main LEGO connection
    }

    public Connection getConnectionToSecondary() throws SQLException {
        return secondaryDataSource.getConnection(); // Using the backup LEGO connection
    }

    // ... your data access methods ...
}
```
- Define Configuration Properties: In your application.properties or application.yml file, you'll create separate blocks of connection details for each database. You'll need things like the JDBC URL, driver class name, username, and password for each. You'll usually prefix these properties to distinguish them (e.g., spring.datasource.primary... and spring.datasource.secondary...).
### application.properties
```java
# Configuration for the primary database (Main LEGO Set)
spring.datasource.primary.url=jdbc:mysql://localhost:3306/main_db
spring.datasource.primary.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.primary.username=user
spring.datasource.primary.password=password

# Configuration for the secondary database (Backup LEGO Set)
spring.datasource.secondary.url=jdbc:postgresql://localhost:5432/backup_db
spring.datasource.secondary.driver-class-name=org.postgresql.Driver
spring.datasource.secondary.username=backup_user
spring.datasource.secondary.password=backup_password
```
- Create Data Source Beans: You'll write configuration classes where you define @Bean methods that create instances of DataSource (usually a HikariCP connection pool). For each database, you'll use the corresponding properties you defined in step 1 to configure its DataSource. You'll often use @ConfigurationProperties with a prefix to easily map the properties to the DataSource configuration.
### DataSourceConfig
```java
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement // Enable Spring's transaction management capabilities
public class DataSourceConfig {

    // 1. Define Configuration Properties & Create DataSource Bean for Primary DB
    @Primary // Marks this as the default DataSource
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary") // Maps properties from application.properties
    public DataSource primaryDataSource() {
        // Using DataSourceBuilder to create a DataSource instance (HikariCP is often the default)
        return DataSourceBuilder.create().build();
    }

    // 2. Define Configuration Properties & Create DataSource Bean for Secondary DB
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary") // Maps properties from application.properties
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    // 3. Manage Transactions: Create TransactionManager for Primary DB
    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryDataSource") DataSource primaryDataSource) {
        // DataSourceTransactionManager is a common implementation for JDBC transactions
        return new DataSourceTransactionManager(primaryDataSource);
    }

    // 4. Manage Transactions: Create TransactionManager for Secondary DB
    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
        return new DataSourceTransactionManager(secondaryDataSource);
    }
}

// Example application.properties:
// spring.datasource.primary.url=jdbc:mysql://localhost:3306/main_db
// spring.datasource.primary.driver-class-name=com.mysql.cj.jdbc.Driver
// spring.datasource.primary.username=user
// spring.datasource.primary.password=password
//
// spring.datasource.secondary.url=jdbc:postgresql://localhost:5432/backup_db
// spring.datasource.secondary.driver-class-name=org.postgresql.Driver
// spring.datasource.secondary.username=backup_user
// spring.datasource.secondary.password=backup_password
```
1. @Configuration and @EnableTransactionManagement:
   - @Configuration:  Indicates that this class provides Spring configuration.
   - @EnableTransactionManagement: Enables Spring's transaction management features, allowing us to use @Transactional.
2. @Primary and @Bean(name = "primaryDataSource"):
   - @Primary:  Marks the primaryDataSource as the default DataSource to be used when a specific one isn't requested.
   - @Bean(name = "primaryDataSource"):  Defines a Spring bean named "primaryDataSource" that represents the DataSource for the primary database.  
3. @ConfigurationProperties(prefix = "spring.datasource.primary"):
   - This is the key annotation that simplifies the configuration.  It tells Spring to automatically bind properties from application.properties that start with spring.datasource.primary to the properties of the DataSource object being created.
   - For example, spring.datasource.primary.url in application.properties will be mapped to the url property of the DataSource.
   - We don't have to manually set each property.  Spring Boot does it for us.
   - If you were using a custom DataSource class with setter methods, Spring would use those setters to set the values.  Since we're using DataSourceBuilder, it handles it internally.
4. DataSourceBuilder.create().build():
   - DataSourceBuilder is a utility class provided by Spring Boot to help create DataSource instances.
   - create():  Initializes the builder.
   - .build():  Constructs the DataSource object using the properties defined in application.properties.  Spring Boot automatically sets these properties because of the @ConfigurationProperties annotation.  By default, Spring Boot often uses HikariCP as the connection pool implementation if it's on the classpath.
5. PlatformTransactionManager Beans:
   - @Primary @Bean(name = "primaryTransactionManager"):  Defines a Spring bean named "primaryTransactionManager" that is a PlatformTransactionManager.  This bean is responsible for managing transactions for the primaryDataSource.  The @Primary annotation marks this transaction manager as the default.
   - DataSourceTransactionManager:  A standard implementation of PlatformTransactionManager for JDBC-based data sources.  It uses the provided DataSource to manage transaction boundaries (commit, rollback).
   - By creating separate PlatformTransactionManager beans, we can control transactions for each database independently.



- Manage Transactions: If you need transactional behavior across multiple data sources, you'll typically need to set up separate PlatformTransactionManager beans for each DataSource and potentially use annotations like @Transactional with a specific transaction manager name to indicate which database's transaction to use.
### MyService.java
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class MyService {

    private final DataSource primaryDataSource;
    private final DataSource secondaryDataSource;

    @Autowired
    public MyService(@Qualifier("primaryDataSource") DataSource primaryDataSource,
                     @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
        this.primaryDataSource = primaryDataSource;
        this.secondaryDataSource = secondaryDataSource;
    }

    // Transaction for the primary database
    @Transactional("primaryTransactionManager")
    public void doSomethingWithPrimary() throws SQLException {
        try (Connection conn = primaryDataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO table1 (data) VALUES (?)")) {
            ps.setString(1, "Data for primary");
            ps.executeUpdate();
            // ... more operations on the primary database ...
        }
    }

    // Transaction for the secondary database
    @Transactional("secondaryTransactionManager")
    public void doSomethingWithSecondary() throws SQLException {
        try (Connection conn = secondaryDataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO table2 (data) VALUES (?)")) {
            ps.setString(1, "Data for secondary");
            ps.executeUpdate();
            // ... more operations on the secondary database ...
        }
    }

    // Transaction spanning both databases (requires more complex coordination, often discouraged)
    //  This is just for illustration and might not be suitable for all use cases.  Consider alternatives.
    @Transactional("primaryTransactionManager") //  Important:  This will only control the *first* transaction.
    public void doSomethingWithBoth() throws SQLException {
        try (Connection primaryConn = primaryDataSource.getConnection();
             PreparedStatement primaryPs = primaryConn.prepareStatement("INSERT INTO table1 (data) VALUES (?)")) {
            primaryPs.setString(1, "Data for primary in both");
            primaryPs.executeUpdate();

            //  Important:  We are *not* using @Transactional for the secondary operation.
            //  This means it's a *separate* transaction.  If the primary transaction rolls back,
            //  this secondary insert will *not* automatically roll back.  This is generally NOT what you want.
            try (Connection secondaryConn = secondaryDataSource.getConnection();
                 PreparedStatement secondaryPs = secondaryConn.prepareStatement("INSERT INTO table2 (data) VALUES (?)")) {
                secondaryPs.setString(1, "Data for secondary in both");
                secondaryPs.executeUpdate();
            }  //  Secondary connection is closed, secondary transaction commits (or rolls back independently).

            if (true) { //  Forced rollback of the *primary* transaction for demonstration.
                throw new RuntimeException("Forced rollback of primary transaction");
            }

        } catch (Exception e) {
            //  Handle exceptions.  The secondary insert *might* have committed already.
            e.printStackTrace();
        }
        //  Primary transaction rolls back here (due to the exception).
    }
}

```
- Transaction Management: The @Transactional annotation is crucial for ensuring data consistency. By specifying the appropriate transaction manager (e.g., "primaryTransactionManager"), you control which database's transaction is used for a given operation. If an exception occurs within a transactional method, the changes made within that transaction will be rolled back.
- Independent Transactions: In the doSomethingWithBoth() example, the transactions for the primary and secondary databases are independent unless you use a distributed transaction manager (like JTA, which is more complex and often has performance implications). This means that if the primary transaction rolls back, the secondary transaction might have already committed. This is often not the desired behavior.
- Distributed Transactions (JTA): For true ACID transactions that span multiple databases, you would need a Java Transaction API (JTA) implementation (like Atomikos or Bitronix) and configure a JtaTransactionManager. This is significantly more complex than using DataSourceTransactionManager and can impact performance, so it should be used only when absolutely necessary.
- 
