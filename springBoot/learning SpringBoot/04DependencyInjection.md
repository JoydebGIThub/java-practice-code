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
