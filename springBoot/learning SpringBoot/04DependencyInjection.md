## Why dependency injection?
- when we create instances by our owns and instantiated then it will `tightly coupled` with that class
- So, in `tight couple` if we want to change the class in interface then the instance we created by our owns will give the error.
- So, to use the interface we create an implementaion of that interface and create a instance with the implemented class and take the interface as reference.
- This implementation is classed **concrete implementation** (A concrete implementation in Java refers to a class that provides a complete implementation of all methods, including those inherited from abstract classes or interfaces).
- And this **concrete implementation** breaks the **solid principal** (Dependency Inversion rules)
  - Dependency Inversion rule of solid principal, don't depend on concrete implementation, rather depend on abstraction
  - Dependency Inversion can be achieved by Dependency Injection
    - We can make classes independent of dependencies.
    - No more `tight coupling`
   
## Type of Dependency Injection
1. Field Injection
2. Setter Injection
3. Constructor Injection (IT industry use this most of the time

### Field Injection:
- Dependency is set into fields of class.
- Spring uses reflection, it iterates over fields and resolve dependencies.

#### Advantages:
- Simple and easy to use

#### Disadvantages:
- Cannot be used with immutable fields -- final (it needs to be initialized when we declared it. When we assign the final variable we cannot change it)
```java
@Autowired
public final Order order;
```
- Chances of NPE (NullPointerException)
```java
public class User{
  @Autowired
  public Order order;

  public void process(){
    order.process();
  }

  public static void main(String[] a){
    User user= new User();
    user.process();
  }
}
```
**If we inject the dependency throw spring then it will working fine but when we just create a normal User object and call the process then the order will give the NullPointerException**

### Setter Injection
```java
@Component
public class User{
Order order;

  public User(){
    System.out.println("Initializing User")
  }

  @Autowired
  public void setOrder(Order order){
    this.order= order;
  }
}
```

#### Advantages:
- Dependency can be changes any time after object creation
- Easy for junit testing, we can pass mock objects in dependency easily

#### Disadvantages:
- field cann't be marked as final
- difficult to read and maintain, as per standards.

### Constructor Injection
- Dependency will be resolved at the time of initialization of object.
- When there is inly one constructor is present `@Autowired` is not mandatory
```java
@Component
public class User{
  Order order;
  @Autowired
  public User(Order order){
    this.order= order;
    System.out.println("Initializing User")
  }
}
```

#### Advantages:
- All mandatory dependencies will be injected at the time of initialization itself;
- Makes 100% sure that our object will be initialized with all the required dependency
- Avoids NPE
- We can create immutable object using constructor injection
- Fail Fast - Fail at compilation only in case of missing dependencies.
```java
@Component
public class User{
  final Order order;
  @Autowired
  public User(Order order){
    this.order= order;
    System.out.println("Initializing User")
  }
}
```
********************************************************************************************************
## Problem faced during DI
- Circular Dependency
```java
@Component
public class User{
  final Order order;
  @Autowired
  public User(Order order){
    this.order= order;
    System.out.println("Initializing User")
  }
}

@Component
public class Order{
  User user;
  @Autowired
  public Order(User user){
    this.user= user;
    System.out.println("Initializing Order")
  }
}
```
- here `Order` is depending on `User` and `User` is depending on `Order`.

### To resolve the issue we can use @Lasy Annotation
```java
@Component
public class Order{
  User user;
  @Autowired
  @Lazy
  public Order(User user){
    this.user= user;
    System.out.println("Initializing Order")
  }
}
```

### To resolve the issue we also can use @PostConstruct annotation
```java
@Component
public class User{
  @Autowired
  Order order;
  
  public User(Order order){
    this.order= order;
    System.out.println("Initializing User")
  }

  @PostConstruct
  public void init(){
    order.setOrder(this);
  }
  
}

@Component
public class Order{
  User user;
  public Order(){
    System.out.println("Initializing Order")
  }
  public void setOrder(User user){
    this.user = user;
  }
}
```
- Unsatisfied dependency
  - When a `interface` is implemented by 2 classes and when we take that `interface` as a type for a reference then JVM will be confused to which class `Bean` should be used thats why its called `Unsatisfied dependency`.
  - So to resolve we can mark one of the class/component as `@Primary`
  - We also can use `@Qualifier` to use the perticular component.
```java
@Component
public class User{
  Order order;

  @Autowired
  public User(@Qualifier("onlineOrder") Order order){
    this.order= order;
    System.out.println("Initializing User")
  }
}
```







