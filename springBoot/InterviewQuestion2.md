# Q: What is Spring Boot? How is it different from Spring Framework?
Ans: Spring Boot is an extension of the Spring Framework that simplifies the process of building stand-alone, production-ready Spring applications.
It removes boilerplate configurations and auto-configures many things behind the scenes.
🔹Spring = Flexible but requires a lot of manual configuration (XMLs, JavaConfig).
🔹Spring Boot = Opinionated defaults, embedded servers (Tomcat/Jetty), production-ready with almost zero configuration.
***********
# Q: What are the advantages of using Spring Boot?
Ans: Spring Boot makes it easy to create production-grade, stand-alone Spring applications with minimal setup, using embedded servers, auto-configuration, starter dependencies, and production-ready monitoring.
Spring Boot simplifies Spring application development by providing auto-configuration, embedded servers like Tomcat, starter dependencies for quick setup, and production-ready features like health checks via Actuator.
It reduces boilerplate code, supports microservices, manages multiple environments easily with profiles, and speeds up both development and testing.
***********
# Q: What is starter dependency in Spring Boot?
Ans: Starter dependencies are pre-defined dependency bundles provided by Spring Boot to simplify Maven configuration.
They group together commonly used libraries for a specific functionality, so you don’t have to manually specify each individual dependency.
## If you want to build a web application, instead of manually adding:
1. Spring MVC
2. Tomcat
3. JSON libraries (like Jackson)
### You just add one starter:
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>

```
***************
# Q: What is Spring Boot Auto-Configuration?
Ans: Auto-Configuration in Spring Boot means Spring Boot automatically configures your application based on the libraries present in the classpath —
without requiring you to write manual configuration (@Configuration) files.
## Example:
If Spring Boot detects spring-boot-starter-data-jpa and a Datasource in the application.properties,
it automatically configures:
1. EntityManagerFactory
2. TransactionManager
3. JPA repositories
👉 No need to manually define these beans.
## 🎯 Key Points:
- It works using the annotation @EnableAutoConfiguration (which is part of @SpringBootApplication).
- It uses classes like spring.factories internally to find which configuration to apply.
- You can override auto-configuration by defining your own beans.
- You can exclude specific auto-configurations if needed:




