## ✅ Step-by-Step: Global Exception Handling with @RestControllerAdvice
### 🔹 Step 1: Create a Custom Exception (Optional)
```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

### 🔹 Step 2: Create a Global Exception Handler
```java

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DataAccessException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle DataAccessException (Spring DB errors)
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDatabaseException(DataAccessException ex) {
        return new ResponseEntity<>("Database Error: " + ex.getRootCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Something went wrong: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

```

### 🔹 Step 3: Example Usage in a Service or Controller
```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        // DataAccessException may be thrown if database is down or query fails
        return userRepository.save(user);
    }
}

```










