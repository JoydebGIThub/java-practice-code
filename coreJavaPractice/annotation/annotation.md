# Annotation
## Core Java Annotations
***
### @Override:
```
Indicates a method override a method from a super class
```
```java
class Animal{
    public void sound(){
        System.out.println("Animal makes sound");
    }
}
class Dog extends Animal{
    @Override
    public void sound(){
        System.out.println("Dog barks");
    }
}
```

### @Deprecated:
```
Marks a method or class as deprecated (not recommended for use).
```
```java
class Animal{
    @Deprecated
    void sound(){
        System.out.println("Animal makes sound");
    }
}
```

### @SuppressWarnings:
```
Tells the compiler to ignore specific warnings
```
```java
@SuppressWarnings("unchecked")
    void example(){
        List list= new ArrayList();
    }
```

### @FunctionalInterface
```
Indicates that an interface is intended to be a functional interface (only one abstract method)
```
```java
@FunctionalInterface
interface MyFunction{
    void doSomething();
}
```
***
## Spring Boot/Spring Framework Annotations
***
### @Component
```
Marks a class as Spring bean/component
```
```java
@Component
public class MyService{
    public void service(){
        System.out.println("Serving...");
    }
}
```

### @Service
```
Specialization of @Component for service layer
```
```java
@Service
public class UserService{
    public void addUser(){
        System.out.println("User added");
    }
}
```

### @Repository
```
Specialization of @Component for DAO (Database Access Object) layer
```
```java
@Repository
public class UserRepository{
    public void save(){
        System.out.println("Saved to DB");
    }
}
```

### @Controller
```
Marks a class as a Spring MVC controller
```

```java
@Controller
public class HomeController{
    @RequestMapping("/")
    public String home(){
        return "index";
    }
}
```

### @RestController
```
Combination of @Controller and @ResponseBody.
```
```java
@RestController
public class ApiController{
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
```

### @RequestMapping
```
Maps HTTP requests to handler methods.
```
```java
@RequestMapping(value="/users", method=RequestMethod.GET)
public List<User> getUsers(){
    return userService.getAllUsers();
}
```

### @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
```
Shortcut annotation for RESTful methods
```
```java
@GetMapping("/user/{id}")
public User getUser(@PathVariable Long id){
    return userService.findById(id);
}
```

### @PathVariable
```
Binds URI template variable to a method parameter
```
```java
@GetMapping("/user/{id}")
public String getUser(@PathVariable int id){
    return "User ID"+id;
}
```

### @RequestParam
```
Extracts query parameters from the URI
```
```java
@GetMapping("/search")
public String search(@RequestParam String keyword){
    return "Searching for: "+keyword
}
```

### @RequestBody
```
Binds the HTTP request body to method paramenter
```
```java
@RestController
public class MyController{
    @PostMapping("/users")
    public String addUser(@RequestBody User user){
        return "User added: " + user.getName();
    }
}
```

### @ResponseBody
```
Indicates that the return value of a method should be used as the response body
```

```java
@RestController
public class MyController{
    @GetMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello world";
    }
}
```

### @Autowird
```
Automatically injects the dependency
```
```java
@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;
}
```

### @Primary
```
It is used to mark a bean as the default condidate when multiple beans of the same type are present in the Spring context
```

```java
@Component
@Primary
public class Car implements Vehicle{
    public void start(){
        System.out.println("Car started");
    }
}

@Component
public class Bike implements Vehicle{
    public void start(){
        System.out.println("Bike Started");
    }
}
```

### @Qualifier
```
1. Specifies which bean to inject when multiple beans of the same type exist
2. if we don't want to use @Primary, we can resolve the ambiguity using @Qualifier
```

```java
@Autowired
@Qualifier("bike")
private Vehicle vehicle;
```

### @Value
```
Injects value from application.properties
```
```java
@Value("${server.port}")
private int port;
```

### @Configuration
```
Indicates the class contains Spring bean definitions
```
```java
@Configuration
public class AppConfig{
    @Bean
    public MyService myService(){
        return new MyService();
    }
}
```

### @Bean
```
Defines a bean method in a @Configurationn class
```
```java
@Bean
public RestTemplate restTemplate(){
    return new RestTemplate;
}
```

### @Entity
```
Marks a class as a JPA entity (table)
```
```java
@Entity
public class User{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
```

### @Table(name="users")
```
Specifies the table name in the DB
```
```java
@Entity
@Table(name="users")
public class User{

}
```

### @Id
```
Marks the primary key field in an entity
```

### @GeneratedValue
```
Auto generates the ID values.
```
```java
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private Long id;
```

### @Column
```
Specifies the column in the table
```
```java
@Column(name="user_name")
private String name;
```
### @ManyToOne, @OneToMany, @OneToOne, @ManyToMany
```
Defines relationship between entities
```
```java
@OneToMany(mappedBy="user")
private List<Order> orders;
```

### @Transactional
```
Manages transaction boundaries
```
```java
@Transactional
public void saveUser(User user){
    userRepository.save(user);
}
```
***
## Spring Validation Annotation
***
### @NotNull, @Size, @Email, @Min, @Max
```
Perform validation on input fields
```
```java
public class UserDTO{
    @NotNull
    @Size(min=2)
    private String name;

    @Email
    private String email;

    @Min(18)
    private int age;
}
```

## JUnit 5 Testing Annotation
***
```
Used for unit testing with JUnit
```
```java
public class UserTest{
    @BeforeEach
    void setup(){
        System.out.println("Setup before each test");
    }

    @Test
    void testAdd(){
        Assertions.assertEquals(4, 2+2);
    }

    @AfterEach
    void cleanup(){
        System.out.println("Cleanup after each test");
    }

    @Disabled
    @Test
    void skippedTest(){}
}
```

***
## Spring Security Annotations
### @EnableWebSecurity
```
Enables web security configuration
```

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

}
```

### @PreAutorize
```
Adds method-level security
```

```java
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin")
public String adminOnly(){
    return "Admin data";
}
```

### @Secured
```
Specifies a list of security roles for method-level security
```
```java
@RestController
public class AdminController{

    @Secured("ROLE.ADMIN")
    @GetMapping("/admin")
    public String adminAccess(){
        return "Admin content";
    }
}
```

***
## Scheduling and Asynchronous Tasks
***
### @EnableScheduling, @Scheduled
```
Runs tasks on a schedule
```
```java
@EnableScheduling
@SpringBootApplication
public class SchedulerApp{}

@Component
public class MyTask{
    @Scheduled(fixedRate= 5000)
    public void printTime(){
        System.out.println("Running task at "+new Date());
    }
}
```

### @EnableAsync, @Async
```
Runs methods asynchronously
```
```java
@EnableAsync
@SpringBootApplication
public class AsyncApp{}

@Service
public class NotificationService{

    @Async
    public void sendEmail(){
        System.out.println("Sending email....");
    }
}
```


