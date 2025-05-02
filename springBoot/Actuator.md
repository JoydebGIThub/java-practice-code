Spring Boot Actuator is a set of production-ready features that help you monitor and manage your Spring Boot application. It provides several built-in endpoints to monitor the health, metrics, environment, and other details about your application. Actuator exposes various services that help you manage the application in production environments. By adding Spring Boot Actuator to your project, you can easily access information about your application’s internal state, performance, and other management aspects without needing to write extra code for it. Spring Boot Actuator enables a wide range of endpoints that provide valuable insights into your application. These endpoints can be enabled or customized based on your needs.

Key Features of Spring Boot Actuator
### Health Checks
Spring Boot Actuator allows you to check if your application is running smoothly, by exposing the /health endpoint. It provides details about various aspects of the application, including database connection, disk space, and external services.
### Metrics
The /metrics endpoint exposes runtime application metrics such as JVM memory, garbage collection, HTTP requests, and more. You can use it to track your app’s performance over time.
### Environment Information
The /env endpoint provides details about the application's environment, such as system properties, environment variables, and configuration properties.
### Application Info
The /info endpoint can be used to expose arbitrary application-specific metadata, such as version numbers, build details, and custom information added by the developer.
### Auditing
Actuator supports auditing, where you can track security-related events (like login attempts), providing a level of security monitoring.
### Thread Dump
The /dump endpoint provides a thread dump, which is useful for diagnosing performance bottlenecks.
### Loggers
The /loggers endpoint allows you to view and modify the logging level of the various loggers within the application.

## Enable the actuator:
- Step 1: add the dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
    <version>3.4.5</version>
</dependency>
```
- step 2: hit the url
```
localhost: 8080/actuator
```
After this configuration we get 3 url to check the condition of our application:
```
health: http://localhost:8080/actuator/health
```
- step 3: enable the enabling endpoint
```properties
management.endpoint.shutdown.enabled = true --only one enable
management.endpoint.web.exposure.include= * -- enable all api
```
