## @Controller
- `Controller class` is a class where we write our actual `APIs`. The API request which is came from the client will 1st come to this `controller` **based on the Mapping**.
```java
package com.springBoot.layerArchitecture.controller;
import org.springframework.stereotype.Controller;
@Controller
public class EmployeeController {

}
```

### The process of the request 1st hit the controller.
- Client --> embeded Server (Tomcat) --> Dispatcher Servlet/ Front Controller --> user HandlerMapping (to find the actual controller for the requestMapping(/getEmp)) --> Then by checking **@Controller** the Dispatcher servlet will find the `controller class` which has the `request mapping` 

![Spring MVC Flow](https://github.com/user-attachments/assets/38e8bae6-2d68-4194-bd1d-7cb1b438563d)

## @RequestMapping
- When the client hit a perticular `path = "/getEmployee"` then dispatcher servlet will find this controller and call the particular API. For HTTP get end point we need to mention that with the path
```java
package com.springBoot.layerArchitecture.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class EmployeeController {
	@RequestMapping(path="/getEmployee", method = RequestMethod.GET)
	public String getEmployee() {
		return "employee"; //employee.html
	}	
}
```

## @ResponseBody
- When we only use the `@Controller` and `@RequestMapping` it will try to find a `.html` or `.jsp` file.
- But when we want to return the **String** or other things we need to use `@ResponseBody`.
- `@ResponseBody` will return the `String` as a **JSON Body** instead of `rendering a UI page`
- It will indicate that the response will be a body not a view
```java
package com.springBoot.layerArchitecture.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class EmployeeController {
	@RequestMapping(path="/getEmployee", method = RequestMethod.GET)
	@ResponseBody
	public String getEmployee() {
		return "employee";
	}	
}
```

## @RestController
- When we have so many method and in every method it returns a body so we need to use `@ResponseBody` in the top of everyMethod.
- We know that the Controller is serving as a RestController so, we can Change the `@Controller` to `@RestCOntroller`.
- **@RestController** is the combination of `@Controller + @ResponseBody`.
- After this we don't need to use `@ResponseBody` in the top of any method.
```java
package com.springBoot.layerArchitecture.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class EmployeeController {
	@RequestMapping(path="/getEmployee", method = RequestMethod.GET)
	public String getEmployee() {
		return "employee";
	}	
}
```
**If there is certain controller is only going to return the data and not going to render any UI then we can directly going with the @RestController instead of @Controller**

## @GetMapping
- In `@RequestMapping` we need to pass the `GET, POST, PUT, DELETE` method explicitly.
- So, in place of `@RequestMapping` for `GET` method we can use `@GetMapping`
- `@GetMapping` is the specialization of `@RequestMapping` in order to get use of `HTTP Get` request.
```java
package com.springBoot.layerArchitecture.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.springBoot.layerArchitecture.entity.Employee;
@RestController
public class EmployeeController {
	@RequestMapping(path="/getEmployee", method = RequestMethod.GET)
	public String getEmployee() {
		return "employee";
	}
	@GetMapping("/getEmployee2")
	public String addEmployee2(Employee emp)
	{
		return "add";
	}
}
```

## @RequestParam:
- When we annoted any value with `@RequestParam`, we will accept any request param the client will be pass to us.
```java
@GetMapping("/getEmployee2")
	public String addEmployee2(@RequestParam(name="id") Employee emp)
	{
		return "add";
	}
```

## @PathVariable
- If we don't pass the value as parameter we can pass the value in path also then we need to use `@PathVariable`
```java
@GetMapping("/getEmployee2/{id}")
	public String addEmployee3(@PathVariable(name="id") Employee emp)
	{
		return "add";
	}
```

## @RequestBody
- When we use this annotation it will automatically map the client provided `JSON body` with the `class`.
- `@RequestBody` will take the `JSON` and converted it into any `class object`
```java
@PostMapping("/getEmployee2")
	public String addEmp(@RequestBody Employee emp)
	{
		return "add";
	}
```

## @Service
- We add this annotation in the top of a class to convert it in a spring managable bean.
- `@Service` is the specialization of the `@Component`
- In this class we write the project business logic

## @Component
- the moment we make a class as `@Component` then this class will manage by the spring.
- We don't need to create an object for this class.
- Spring will create the object for us, the initialization of the object is manage by spring.
- The class will be converted as `Spring Managed Bean`

## @Repository
- The perticular class will talk to DB so we mark it as `@Repository`

## @Autowired
- When I want a class to be automaically injected in a different class then we use this annotation
- By using the annotation spring will do the `Dependency Injection` by there own.











