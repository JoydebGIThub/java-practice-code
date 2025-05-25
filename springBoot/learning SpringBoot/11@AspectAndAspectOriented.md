## Spring AOP (Aspect Oriented Programming)
- Aspect-oriented Programming complement Object-oriented Programming. OOP has object and AOP has Aspect.
- AOP will enable handling `crosscuting` concerns. The things we do apart from `business logic`.
- Let assume we have a class that handle the business logic and we have other code that handle logger which is not a part of business logic, So it is a `crosscuting` system which is different from the `business logic`.
- So, Aspects `enable` of seperating the `crosscutting` system from the actual methods.

### Teminology of AOP:
- `join point`: method execution
- `Advice`: Action taken by an aspect at a particular join point
- `point cut`: It will define which particular method to call in which class. Which method the `Aspect` should applied on.
- `AOP proxy`: An object created by the AOP framework in order to implemenr the aspect contracts

#### AOP proxy
- Spring AOP is proxy-based
- When someone is calling the code, the call will first lend in proxy then the call will be diverted to the `plain object`.
- proxy can `delegate` to all of the interceptors(advice) that are relevent to the particular method call
- proxy will 1st `invoke` the `Aspect method` and the respective `method in the another class`.

##### 2 type of proxy:
- JDK dynamic proxies. If the class implementing the interface then it will be used.
- CGLIB prox, it used if a business object does not implement an interface. Only for plan classes

### Things we needed:
1. enable the aspect by using `@EnableAspectAutoProxy` in the `@Configuration` class
2. declaring aspect by creating a apect class and add `@Aspect` to make the class Aspect
3. converted the aspect to bean we add `@Component`
4. Add some advice method now inside the class
```java
@Component
@Aspect
public class LoggingAspect{
  public void log(){
    System.out.println("Aspect log called");
  }
}
```
5. point cut:
   - declaring advice:
     a. Before Advice: add a `execution` and give the exect function we need to intercept: 1st we give the `retrun type` then `package+method` then any parameter by pass (..). Before `execution of the addProduct` we need to `invoke the log method`. It is called the pointcut expression
```java
@Component
@Aspect
public class LoggingAspect{
  @Before("execution(* com.joydeb.Service.ProductService.addProduct(..))")
  public void log(){
    System.out.println("Aspect log called");
  }
}
```
    b. After Advice: If we want to call the log after the addProduct then we need to just replace the `@Before` with `@After`.
    c. Around Advice: If we want the `Before` and `After` both then we use `@Around`. So for around we need some `joint point` by which it will decide which should execute before and which should execute after. So we use explicite call and we use `ProceedingJoinPoint`
```java
@Component
@Aspect
public class LoggingAspect{
  @Around("execution(* com.joydeb.Service.*.*(..))")
  public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
    System.out.println("Aspect log called");
    //method/joinpoint call
    Object result= joinPoint.proceed();// join point invoke
    System.out.println("Aspect after log called");
    return result;
    
  }
}
```
