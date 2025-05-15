## Scope of Bean
- It define the life cycle and the visibility of the `Bean`.

## Types:
1. Singleton
2. Prototype
3. Request
4. Session

### Singleton:
- `Only single` bean will be injected.
- If we don't define any scope then the **default** scope will be `Singleton`
```java
package com.example.scope.Entity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
@Component
@Scope("singleton")
public class User {
	public User() {
		System.out.println("User initialiation");
	}	
	@PostConstruct
	public void init() {
		System.out.println("User object hashCode: "+this.hashCode());
	}
}
```
- `Singleton scope` will be **eagerly initialize**. Means the `Bean` will be initialize when we `Start` our `Application`
- Without hiting the API the `Bean` will be initialized
- It will never create a `new Bean` it will only initialize a `Bean` one time

### Prototype:
- Each time **new object created**.
- **Lazily initialized** - new object is created each time
- When it is required that time it will be created.
- The `Bean` will be initialize when the API will hit or we do some operations.
- Also when the API hit it will check and create every `Bean` it needed if the `Bean` is already initialize it againg create a new `Bean`
```java
package com.example.scope.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.scope.Entity.User;
import jakarta.annotation.PostConstruct;
@RestController
@RequestMapping(value = "/api")
@Scope("prototype")
public class UserController {
	@Autowired
	User user;
	public UserController() {
		// TODO Auto-generated constructor stub
		System.out.println("Usercontroller init");
	}
	@PostConstruct
	public void init() {
		System.out.println("User controller hascode: "+ this.hashCode() + " User class hashcode: "+user.hashCode());
	}	
	@GetMapping("/fetchUser1")
	public ResponseEntity<?> getUserDetails(){
		System.out.println("Fetch the user details");
		return ResponseEntity.status(HttpStatus.OK).body("Ok");
	}
}
```

### Request
- One Bean per HTTP request
- Lazily initialized
- proxyMode - injecting request scoped been in singleton scoped bean
- if we referesh the API then also new Bean will be created
```java
package com.example.scope.Entity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
@Component
@Scope("request")
public class User {
	public User() {
		System.out.println("User initialiation");
	}	
	@PostConstruct
	public void init() {
		System.out.println("User object hashCode: "+this.hashCode());
	}
}
```
- When we try to inject the `request` bean inside the `singleton` bean that creates a problem because the `request` bean cannot be added inside the `singleton` bean unless we have a `ongoing fetch api` or anyouther api running
- So, for resolving the issue we can add a proxy, so when the `singleton` bean try to create a object of `request` bean it will create a proxy for the `request` bean and inject it in `singleton` bean. It will not be a actual object it will be just a proxy.
**Singleton**
```java
package com.example.scope.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
@Component
public class Employee {
	@Autowired
	User user;
	public Employee() {
		// TODO Auto-generated constructor stub
		System.out.println("Employee initialiation");
	}	
	@PostConstruct
	public void init() {
		System.out.println("Employee object hashCode: "+this.hashCode());
	}
}
```
**request with proxy**
```java
package com.example.scope.Entity;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
@Component
@Scope(value= "request", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class User {
	public User() {
		System.out.println("User initialiation");
	}	
	@PostConstruct
	public void init() {
		System.out.println("User object hashCode: "+this.hashCode());
	}
}
```

### Session
- It's similar to `request`
- New Object for `each HTTP session`
- Lazily Initialized
- When user access any api, session is created
- Remains active till session is expired
- After several refresh new Bean will not created
```java
package com.example.scope.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.scope.Entity.Employee;
import com.example.scope.Entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping(value = "/api")
@Scope("session")
public class EmployeeController {
	@Autowired
	User user;
	@Autowired
	Employee employee;	
	public EmployeeController() {
		System.out.println("Employee controller is created");
	}
	@PostConstruct
	public void init() {
		System.out.println("Employee controller hascode: "+ this.hashCode() + " User class hashcode: "+user.hashCode());
	}
	@GetMapping("/fetchUser")
	public ResponseEntity<?> getUserDetails(){
		System.out.println("Fetch the user details");
		return ResponseEntity.status(HttpStatus.OK).body("Ok");
	}	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request){
		System.out.println("logout details");
		request.getSession().invalidate();
		return ResponseEntity.status(HttpStatus.OK).body("logout");
	}
}
```






