## What is RestAPI?
Ans: Rest stands for "**Representational State Transfer**" and API stands for "**Application Programming Interface**".
It is an **architectural style** for designing **web services** that work over **HTTP**. A **REST API** (or RESTful API) allows different systems (like frontend and backend) to **communicate using standard HTTP** methods.
### 🔹 Key Concepts of REST API:
| Concept         | Description                                                                                                |
|-----------------|------------------------------------------------------------------------------------------------------------|
| Client-Server Separation | Clear division between the client (frontend/UI) responsible for presentation and the server (backend/data) responsible for data storage and processing. |
| Stateless       | Each request from the client to the server is independent and contains all the necessary information. The server does not store any session state about the client between requests. |
| Resources       | Everything that can be accessed and manipulated is treated as a resource. Each resource is uniquely identified by a Uniform Resource Identifier (URI), such as `/users/1` or `/products`. |
| HTTP Methods    | Leverages standard HTTP methods to perform actions on resources:                                          |
|                 | - `GET`: Retrieve a resource.                                                                           |
|                 | - `POST`: Create a new resource.                                                                          |
|                 | - `PUT`: Update an existing resource.                                                                       |
|                 | - `DELETE`: Remove a resource.                                                                         |
|                 | - (and others like PATCH, OPTIONS, HEAD)                                                                   |
| JSON/XML        | Data exchanged between the client and the server is typically formatted using JSON (JavaScript Object Notation) or XML (Extensible Markup Language). |

### 🚀 1. GET – Read a resource
#### ✅ Purpose:
- Fetch data from the server (like reading user details).
```java
package com.restApi.restApiExplanation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserConroller {
	
	@GetMapping("/{id}")
	public String getUser(@PathVariable int id) {
		return "The user id: "+ id;
	}

}
```
#### Url to hit:
```
Get: http://localhost:8080/users/1
```
### 🚀 2. POST – Create a new resource
#### ✅ Purpose:
Send data to the server to create a new resource.
```java
public class User{
  private String name;

  public void setName(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }  
}
```
```java

@RestController
@RequestMapping("/users")
public class UserConroller{
  @PostMapping
  public String createUser(@RequestBody User user){
    return "The user name is: "+ user.getName();
  }
}

```
#### Url to hit:
```
POST http://localhost:8080/users
Content-Type: application/json

{
  "name": "John"
}
```
### 🚀 3. PUT – Update a resource
#### ✅ Purpose:
Update or replace a resource completely.
```java
@PutMapping("/{id}")
	public String updateUser(@PathVariable int id, @RequestBody User user) {
		return "User with ID " + id + " updated to name: " + user.getName();
	}
```
#### Url to hit:
```
PUT http://localhost:8080/users/101
Content-Type: application/json

{
  "name": "Updated John"
}
```
#### Output:
```
User with ID 101 updated to name: Updated John
```
### 🚀 4. PATCH – Partially update a resource
#### ✅ Purpose:
Update a part of the resource (not the whole object).
```java
@PatchMapping("/{id}")
public String patchUser(@PathVariable int id, @RequestBody Map<String, String> updates) {
    return "User with ID " + id + " updated field: " + updates;
}
```
#### Url to hit:
```
PATCH http://localhost:8080/users/101
Content-Type: application/json

{
  "name": "Partial John"
}

```
#### Output:
```
User with ID 101 updated field: {name=Partial John}

```
### 🚀 5. DELETE – Remove a resource
#### ✅ Purpose:
Delete a resource by ID.
```java
@DeleteMapping("/{id}")
public String deleteUser(@PathVariable int id) {
    return "User with ID " + id + " deleted";
}

```
#### Url to hit:
```
DELETE http://localhost:8080/users/101
```
