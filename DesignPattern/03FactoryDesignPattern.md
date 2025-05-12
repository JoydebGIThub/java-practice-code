## Factory Design pattern / Factory Method Design pattern:
- When there is **superclass** and **multiple subclasses** and we want to get **object of the subClasses** based on input and requirement.
- Then we create **factory class** which takes the **responsibility of creating object of class based on input**.

### Advantages of Factory Design Pattern:
- Focus on creating object for instance rather than implementation.
- Loose coupling, more robust code.

```java
import java.io.*;
class Main {
    public static void main(String[] args) {
        //creating object by client itself
        Employee e = new AndroidDeveloper(); //tightly couple
        
        //creating object responsibility giving to the EmployeeFactory
        Employee employee = EmployeeFactory.getEmployee("WEB DEVELOPER");
        System.out.println(employee.salary());
    }
}
interface Employee{
    int salary();
}

class AndroidDeveloper implements Employee{
    public int salary(){
        System.out.println("Getting android developer salary");
        return 50000;
    }
}

class WebDeveloper implements Employee{
    public int salary(){
        System.out.println("Getting web developer salary");
        return 40000;
    }
}

class EmployeeFactory{
    // Retrun the Employee object based on input
    // We don't want to give the responsibility to client to create the object for a particular class
    
    //get The employee
    public static Employee getEmployee(String empType){
        if(empType.trim().equalsIgnoreCase("ANDROID DEVELOPER")){
            return new AndroidDeveloper();
        }else if(empType.trim().equalsIgnoreCase("WEB DEVELOPER")){
            return new WebDeveloper();
        }
        return null;
    }
}
```
**Note: EmployeeFactory is a Factory class that return a class object based on the passing parameter**
### Output:
```
Getting web developer salary
40000
```




