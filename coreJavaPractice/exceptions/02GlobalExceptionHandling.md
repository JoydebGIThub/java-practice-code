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
************************************************************************************
## Traditional way to handle exception:
- Create a ErrorMessage class:
```java
package com.exceptionHandling.entity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
	private LocalDateTime date;
	private String message;
	private String status;
}
```
- Create a Service for all the calculation
```java
package com.exceptionHandling.service;
import org.springframework.stereotype.Service;

@Service
public class MyService {	
	public int division(int a, int b) throws ArithmeticException {
		return a / b;
	}
}
```
- Create the controller to communicate with the client through RestApi
```java
package com.exceptionHandling.controller;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.exceptionHandling.entity.ErrorMessage;
import com.exceptionHandling.service.MyService;

@RestController
public class MyController {	
	@Autowired
	private MyService service;
	
	@GetMapping("/division")
	public ResponseEntity<?> division(@RequestParam int a, @RequestParam int b ){
		int result = 0;
		try {
			result = service.division(a, b);
		} catch (ArithmeticException e) {
			ErrorMessage error = new ErrorMessage(LocalDateTime.now(), e.getMessage(), "Please select the correct inputs");
			return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
```
- Here in controller we use **RestController** for sending the response with **ResponseBody**.
- In **ResponseEntity** we use **Generic Type (?)** so that we can pass any type through this response.
- here we handle the error through **try-catch** block and store the error as a message in **ErrorMessage** and send as a response when we get the **ArithmeticException**

### In Traditional way we fetch some issue.
- if we have multiple method then we need to use **try-catch** block for every method.
- Its kind of code duplication
- So, we create a seperate method and use "**@ExceptionHandler**" it will help us to remove the extra **try-catch**
```java
    @GetMapping("/division")
	public ResponseEntity<?> division(@RequestParam int a, @RequestParam int b ){
		Integer result = Optional.ofNullable(service.division(a, b)).orElseThrow(() -> new ArithmeticException("Plese enter the correct value"));
		return new ResponseEntity<Integer>(result, HttpStatus.OK);	
	}
    @GetMapping("/division2")
	public ResponseEntity<?> division2(@RequestParam int a, @RequestParam int b ){
		Integer result = Optional.ofNullable(service.division(a, b)).orElseThrow(() -> new ArithmeticException("Plese enter the correct value"));
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

    @ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<?> handleTheException(ArithmeticException e){
		ErrorMessage error = new ErrorMessage(LocalDateTime.now(), e.getMessage(), "Please select the correct inputs");
		return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
	}
```
- Here we use the **Optional.ofNullable** to accept any null value and also to perform the "**orElseThrow**" method because it cann't be apply on `int` type.
- By using the `@ExceptionHandler(ArithmeticException.class)` we can catch any throw exception related to `ArithmeticException`.

## But still it only handle the error inside a particuler class.
- So if we have many controller and they are fetching some error and need exception handling then we need to add the **handleTheException** method in every class.
- And here we **repeat** the same code again and again
- So, we use the **ControllerAdvice** here. It allows us to add our Exception handler in a **Generic** place or we can say it help us to **centralized** our `Exception Handler`.

```java
package com.exceptionHandling.exception;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.exceptionHandling.entity.ErrorMessage;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<?> handleTheException(ArithmeticException e){
		ErrorMessage error = new ErrorMessage(LocalDateTime.now(), e.getMessage(), "Please select the correct inputs");
		return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
```
- Now this will handle all the exception in any class in my project globally.

- Also we can use "**RestControllerAdvice**" = `ResponseBody + ControllerAdvice`
- In `ControllerAdvice` it will working fine untill we use `ResponseEntity` but if we want to send the `String` then **ControllerAdvice** try to find the find a view and give a error like "**unspecified view**". For sending the `String` we need to add the `@ResponseBody` over the method to check the `String` as response.
- So if we don't need to use the **@ResponseBody** over the method then we Can use the **RestControllerAdvice**

```java
package com.exceptionHandling.exception;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.exceptionHandling.entity.ErrorMessage;

@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler(ArithmeticException.class)
	public String handleTheException(ArithmeticException e){
		ErrorMessage error = new ErrorMessage(LocalDateTime.now(), e.getMessage(), "Please select the correct inputs");
		return "Error";
	}
}
```







