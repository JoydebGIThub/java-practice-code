## Q: What are the advantages the spring boot have over spring?
While Spring Boot is built on top of the core Spring Framework, it offers several key advantages that make it a popular choice for modern Java development, especially for microservices and rapid application development:
1. Simplified Setup and Configuration (Auto-Configuration):
   -
   - Spring: Traditionally, Spring applications required significant manual configuration, often involving numerous XML files or extensive Java-based configurations using annotations. Setting up even basic functionalities could be verbose and time-consuming.
   - Spring Boot: Embraces an "opinionated" approach and provides auto-configuration. Based on the dependencies you add to your project (e.g., a web starter, a data JPA starter), Spring Boot automatically configures the necessary Spring beans and infrastructure with sensible defaults. This drastically reduces the amount of manual configuration needed.
2. Starter Dependencies:
   -
   - Spring: Managing dependencies in a Spring project could involve manually adding and ensuring compatibility between various Spring modules and third-party libraries.
   - Spring Boot: Introduces "Starter POMs" (for Maven) or "Starter Gradle" dependencies. These are convenient dependency descriptors that bundle all the necessary dependencies for a specific type of application (e.g., spring-boot-starter-web for web applications, spring-boot-starter-data-jpa for JPA-based data access). This simplifies dependency management and reduces the risk of version conflicts
3. Embedded Web Servers:
   -
   - Spring: Deploying a Spring web application typically required setting up and configuring an external web server like Tomcat, Jetty, or Undertow, and then deploying your application as a WAR file.
   - Spring Boot: Provides embedded web servers (Tomcat, Jetty, Undertow) out of the box. You can package your application as an executable JAR file and run it directly using java -jar. This simplifies deployment and makes it easier to create standalone applications.
4. Actuator for Monitoring and Management:
   -
   - Spring: While Spring provides some basic monitoring capabilities, setting up comprehensive monitoring and management often required additional libraries and configurations.
   - Spring Boot: Includes Spring Boot Actuator, which exposes various production-ready endpoints for monitoring and managing your application. These endpoints provide insights into health checks, metrics, environment details, thread dumps, and more, with minimal configuration
*************************************************************
## Q: Global exception handler in spring boot?
A global exception handler in Spring Boot allows you to centralize the handling of exceptions that are thrown across your entire application. Instead of having try-catch blocks in every controller method, you can define one or more methods in a dedicated class to handle specific types of exceptions globally. This leads to cleaner, more maintainable, and consistent error handling.
1. Create a Class Annotated with @ControllerAdvice or @RestControllerAdvice:
   -
   - @ControllerAdvice: This annotation is a specialization of @Component that allows you to handle exceptions across all @Controller annotated classes. It's typically used when you need to render custom error pages or return model attributes along with the error response.
   - @RestControllerAdvice: This annotation is a convenience annotation that combines @ControllerAdvice and @ResponseBody. It's particularly useful for building RESTful APIs where you want to return error responses in formats like JSON or XML.
```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice // Or @RestControllerAdvice
public class GlobalExceptionHandler {

    // Exception handling methods will go here
}
```
2. Define Exception Handling Methods using @ExceptionHandler:
   -
   - Within your @ControllerAdvice or @RestControllerAdvice class, you define methods that will handle specific types of exceptions. You use the @ExceptionHandler annotation to specify the exception class (or classes) that the method should handle.
```java
@ExceptionHandler(ResourceNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ResponseBody
   public ErrorResponse handleResourceNotFound(ResourceNotFoundException ex) {
       return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
   }

   // Define a simple ErrorResponse class
   record ErrorResponse(int status, String message) {}

   // Assume you have a custom exception like this:
   class ResourceNotFoundException extends RuntimeException {
       public ResourceNotFoundException(String message) {
           super(message);
       }
   }
```
- @ExceptionHandler(ResourceNotFoundException.class) indicates that this method will handle exceptions of type ResourceNotFoundException.
- @ResponseStatus(HttpStatus.NOT_FOUND) sets the HTTP status code of the response to 404 (Not Found).
- @ResponseBody indicates that the return value should be directly written to the HTTP response body (typically as JSON or XML when using @RestControllerAdvice).
- The handleResourceNotFound method takes an instance of ResourceNotFoundException as an argument, allowing you to access the exception's details.
**general exception handling**
```java
import org.slf4j.Logger;
   import org.slf4j.LoggerFactory;
   import org.springframework.http.HttpStatus;
   import org.springframework.http.ResponseEntity;

   private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

   @ExceptionHandler(Exception.class)
   public ResponseEntity<String> handleGenericException(Exception ex) {
       logger.error("An unexpected error occurred", ex);
       return new ResponseEntity<>("An unexpected error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
   }
```
************************************************************************
## Different status code?
**2xx: Success**
These codes indicate that the client's request was successfully received, understood, and accepted.
1. 200 OK: The standard response for successful HTTP requests. The actual response content depends on the method used.
**4xx: Client Error**
These codes indicate that the client's request contained an error and cannot be fulfilled.
1. 400 Bad Request: The server cannot understand the request due to invalid syntax.
2. 401 Unauthorized: The client must authenticate itself to get the requested response.
3. 402 Payment Required: Reserved for future use.
4. 403 Forbidden: The server understood the request but refuses to authorize it. Unlike 401, re-authenticating will not help.
5. 404 Not Found: The server has not found anything matching the Request-URI
**5xx: Server Error**
These codes indicate that the server encountered an error while trying to fulfill the request.
1. 500 Internal Server Error: A generic error message, given when an unexpected condition was encountered and no more specific message is suitable.
2. 502 Bad Gateway: The server, while acting as a gateway or proxy, received an invalid response from the upstream server it accessed in attempting to fulfill the request.






