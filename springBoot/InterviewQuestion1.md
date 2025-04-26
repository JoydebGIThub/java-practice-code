# Q: What is Spring Boot and why do we use it?
Ans: Spring Boot is a framework built on top of Spring that makes it easy to create stand-alone, production-ready applications with minimal setup (no XML config, embedded server, auto-config, etc.).

# Q: What is @SpringBootApplication? What does it really do?
Ans: It’s a combination of three annotations:
- @Configuration (marks class as source of bean definitions)
- @EnableAutoConfiguration (auto-configures based on dependencies)
- @ComponentScan (scans the package for components)

# Q: Explain how Spring Boot AutoConfiguration works internally.
Ans: 
- Spring Boot uses @EnableAutoConfiguration.
- It reads META-INF/spring.factories.
- Based on the classpath (what libs you have) and properties, it automatically configures beans you might need (like DataSource, Jackson, etc.).

# Q: How do you create a custom AutoConfiguration?
Ans: 
- Create a @Configuration class.
- Annotate it with @ConditionalOnClass, @ConditionalOnMissingBean, etc.
- Register it inside META-INF/spring.factories under EnableAutoConfiguration.

# Q: What is the difference between @Component, @Service, @Repository, and @Controller?
Ans: All are Spring-managed beans but with different definition:
- @Component: generic bean
- @Service: service layer logic
- @Repository: DAO, data access, exception translation
- @Controller: MVC web controller (returns views)

# Q: What is application.properties vs application.yml?
Ans: Both are used to define configuration:
- application.properties: key=value style
- application.yml: YAML (hierarchical, cleaner for complex settings)
Spring Boot supports both.

# Q: What is a Spring Boot Starter?
Ans: It’s a set of pre-defined dependencies (pom.xml bundles) to quickly start working on a feature.
- spring-boot-starter-web → Tomcat, Jackson, Spring MVC, etc.

# Q: What is Spring Boot DevTools?
Ans: It enables:
- Automatic restart
- Live reload in browser
- Development-time configurations
Makes dev experience much faster.

# Q: How do you handle exceptions globally in Spring Boot?
Ans: Using @ControllerAdvice + @ExceptionHandler methods.

# Q: What is the difference between @RestController and @Controller?
Ans: 
- @Controller returns views (like JSPs, Thymeleaf).
- @RestController = @Controller + @ResponseBody → directly returns JSON or raw data.




