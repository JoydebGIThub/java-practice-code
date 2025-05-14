## Project Metadata:
- Group: is like a `company name` and we write it like backwords `com.accenture`

## Packaging:
- jar = it means `Java Archive`. It will use when we want to implement a **Standalone Application**. `Standalone` means we start the application and the request will directly come to that and application will give the response. It's like a `backend application`
- war = it means `Web Archive`. When we want to create a **Web Application**. Here we have html, css, UI, and UI will take data from application and we deply it and user will intaract with UI, this kind of web application we go for WAR.

**********************************************************************************************
## Layered Architecture
- It is excepted by most of the organization and they will following it when writing or creating the `Spring boot application`.
- 1st client send a request to `Controller Layer` it will forward the details to `Service Layer` then it communicate with the `Repository Layer`.
  - **Controller layer**: we write the `Controller` here, and it is the 1st `entry point` in our spring application. Here we write our `API`. Here we use **@Controller and @RestController** at the top of the class. And inside the class we have API that client will call. Job of the controller is to `get the request` and `give a response`.
  - **Service Layer**: All the `Business Operations` will be happened in `Service layer`. It will request the `Repository layer` for some data from the `database`. After the data fetched from the db is completed the `Service Layer` will send the data to `Controller layer`
  - **Repository Layer**: Will communicate with the `DB` and give the data to the `Service layer`.
 
- There are some important part also:
  - DTO -> Data Transfer Object. Let assume client is request for some `post request` and it will send some `Request Body`/ `JSON object` which will have the details and that need to converted into a java object inside the controller layer and that particuler java object is called **DTO**. DTO is used for client to controller communication. Request DTO will have request details and Response DTO will have response details.
  - Entity -> There will be a Java object when we call the database, its similar to `DTO` but `Entity` will be direct connector with `DB`. Convertion from `Entity` to `DTO` in the `Service layer`. In Entity we have many properties but in DTO we can only give some of the properties which is needed by the client. **Entity** will be the direct mapping of the table inside the DB.
  - Utility -> There is something common in between the `3 layer` we can put that inside `utility` and we can import and use it.
  - Config -> Here we can have common values, common urls, common configaration that i want to use in the entire application. Example: there is a 3rd party url that is configer in the `application.properties` and use that in the config and we can use that in entire application.

*******************************************************************************
## Code:
- Controller
```java
package com.springBoot.layerArchitecture.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springBoot.layerArchitecture.dto.EmployeeDto;
import com.springBoot.layerArchitecture.service.MyService;
@RestController
public class MyController {
	@Autowired
	MyService service;	
	@GetMapping("/")
	public EmployeeDto getData() {
		return service.getData();
	}
}
```
- Service
```java
package com.springBoot.layerArchitecture.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springBoot.layerArchitecture.dto.EmployeeDto;
import com.springBoot.layerArchitecture.entity.Employee;
import com.springBoot.layerArchitecture.repository.MyRepository;
@Service
public class MyService {
	@Autowired
	MyRepository repo;	
	public EmployeeDto getData() {
		Employee emp = repo.getData();
		EmployeeDto dto= new EmployeeDto();
		return dto.employeeManager(emp);
	}
}
```
- Repository
```java
package com.springBoot.layerArchitecture.repository;
import org.springframework.stereotype.Repository;
import com.springBoot.layerArchitecture.entity.Employee;
@Repository
public class MyRepository {	
	public Employee getData() {
		return new Employee(10, "Joydeb", "Kolkata");
	}
}
```
- Entity
```java
package com.springBoot.layerArchitecture.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Employee {
	@Id
	private Integer id;
	private String name;
	private String address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Employee(Integer id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}	
}
```
- DTO
```java
package com.springBoot.layerArchitecture.dto;
import com.springBoot.layerArchitecture.entity.Employee;
public class EmployeeDto {	
	private String name;
	private String address;	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public EmployeeDto employeeManager(Employee emp) {
		this.setName(emp.getName());
		this.setAddress(emp.getAddress());
		return this;
	}
}
```
