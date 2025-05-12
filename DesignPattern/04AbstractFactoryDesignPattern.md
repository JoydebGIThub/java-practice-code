## Abstract Factory Design Pattern:
- Here we add an **extra layer** we call a Class and that class will call another class so the 1st class has no idea for which class object is creating
- In this kind of **Design pattern** object is creating on runtime using the parameter.
- It similar to Factory pattern but it provide the concept of **Factory of Factories**
```java
import java.io.*;
class Main {
    public static void main(String[] args) {
       
        Employee employee = EmployeeFactory.getEmployee(new AndroidDevFactory());
        System.out.println(employee.salary());
    }
}
interface Employee{
    int salary();
    String name();
}

class AndroidDeveloper implements Employee{
    public int salary(){
        System.out.println("Getting android developer salary");
        return 50000;
    }
    
    public String name(){
        System.out.println("I am android developer");
        return "Android Developer";
    }
}

class WebDeveloper implements Employee{
    public int salary(){
        System.out.println("Getting web developer salary");
        return 40000;
    }
    public String name(){
        System.out.println("I am web developer");
        return "Web Developer";
    }
}

class EmployeeFactory{
    //get employee
    public static Employee getEmployee(EmployeeAbstractFactory factory){
        return factory.createEmployee();
    }
}

abstract class EmployeeAbstractFactory{
    public abstract Employee createEmployee();
}

class AndroidDevFactory extends EmployeeAbstractFactory{
    public Employee createEmployee(){
        return new AndroidDeveloper();
    }
}
class WebDevFactory extends EmployeeAbstractFactory{
    public Employee createEmployee(){
        return new WebDeveloper();
    }
}
```
### Output:
```
Getting android developer salary
50000
```

## Factory Design Pattern and Abstract Factory Design pattern difference:
- In Factory Design Pattern Factory itself create the object based on the parameter.
- But in Abstract Factory Design pattern Factory has no idea whose object needs to be created
