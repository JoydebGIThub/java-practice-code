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
