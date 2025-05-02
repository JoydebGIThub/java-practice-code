## What is caching in Spring boot?
- Caching in Spring Boot is a mechanism to store frequently accessed data in memory (or a faster data store) so that subsequent requests for the same data can be served more quickly, without needing to retrieve it from the original, potentially slower source (like a database, external API, or file system).   

Think of it like this: Imagine you frequently ask someone for the definition of a specific word. The first time, they might have to look it up in a dictionary (the slower source). But if you ask for the same word again soon after, they might remember the definition and tell you immediately (the cache).
****************************************************************************************************************************************
## How to implement caching in spring boot?
1. Add a Caching Dependency:
```xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```
2. Enable Caching:
Add the **@EnableCaching** annotation to one of your @Configuration classes or your main application class (annotated with @SpringBootApplication).
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class YourSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(YourSpringBootApplication.class, args);
    }
}
```
3. Configure a Cache Manager (Optional but Recommended for Specific Providers):
For providers other than the simple in-memory cache, you might need to configure a CacheManager bean. Spring Boot often provides auto-configuration for common providers, but you might want to customize it.
4. Annotate Methods for Caching:
- @Cacheable("cacheName"): This annotation is used on a method to indicate that its result should be cached. Before the method is executed, Spring checks if the result for the given parameters is already present in the specified cache. If it is, the cached result is returned; otherwise, the method is executed, and its result is stored in the cache before being returned.
```java
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Cacheable("users")
    public User getUserById(Long id) {
        System.out.println("Fetching user from database for ID: " + id);
        // Simulate fetching from database
        return new User(id, "user" + id, "user" + id + "@example.com");
    }
}
```
****************************************************************************************
## What is auto-configuration is spring boot?
Ans: @EnableAutoConfiguration: It automatically configures the spring framework and its components based on the dependencies and configuration present in your application's class path
*****************************************************************************************
## What is the use of DevTool library in spring boot?
Ans: With DevTools enables, any changes you make to your source code, resources or configuration files are automatically detected and trigger a quick application restart. This eliminates the need to manually stop and restart the application during developmen, saving time and improving productivity.
