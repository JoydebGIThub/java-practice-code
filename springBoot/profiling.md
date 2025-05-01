## Profiling
Ans: A profile in Spring is a way to segregate parts of your application configuration so that they are only active in certain environments. This allows you to:
- Load environment-specific beans
- Apply different property files
- Enable or disable certain features based on environment
#### Step1: Define profile specific properties:
```
application-dev.properties
application-test.properties
application-prod.properties
```
- application-dev.properties
```java
username = devUserName
password = devPassword
```
- application-prod.properties
```java
username = prodUserName
password = prodPassword
```
#### Step2: 
- application.properties
```java
spring.application.name=profiling
username = UserName
password = Password
```
#### Step3: active the dev properties:
```java
spring.application.name=profiling
username = UserName
password = Password
spring.profiles.active = dev
```
#### If we mention active = qa and the application-qa.properties is not present:
- Then the data will be fetch from the parent application.properties
#### If the properties is also missing in the parent application.properties
- The application broke
************************************************************************************
In the above we active the profile through changing inside the code:
### But now we want to active the profile externally when the application is running:
- Let we have a jar we can put that jar inside the prod/dev environment and provide the active profile dynamically.
#### Profile config Dynamically:
- 2 ways
  1. while application startup using command [mvn spring-boot:run -Dspring-boot-run.profiles = prod]
  2. Add profile in pom.xml and run using [mvn spring-boot:run -Pproduction
```xml
<project>
  <build>

  </build>
  <profiles>
    <profile>
      <id>local</id>
      <properties>
        <spring-boot:run.profiles>dev</spring-boot:run.profiles>
      </properties>
    </profile>
    <profile>
      <id>production</id>
      <properties>
        <spring-boot:run.profiles>prod</spring-boot:run.profiles>
      </properties>
    </profile>
  </profiles>
</project>
```
************************************************************************************
### @Profile Annotation:
- Using Profile annotation we can tell spring boot, to **create a bean** only when particular profile is set
```java
@Component
public class DBConnection{
  @Value("${username}")
  String username;
  @Value("${password}")
  String password;

  @PostConstruct
  public void init(){
    System.out.println("DBConnection init");
    System.out.println("username: "+username + " | password: "+password);
  }
  
}

```

```java
@Component
@Profile("dev")
public class MySQLConnection{
  @Value("${username}")
  String username;
  @Value("${password}")
  String password;

  @PostConstruct
  public void init(){
    System.out.println("MySQLConnection init");
    System.out.println("username: "+username + " | password: "+password);
  }
  
}

```
********************************************************************************************
## Step-by-Step: Creating a test Profile
1. Create application-test.properties
```xml
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

```
2. Activate the test Profile in a Test Class
```java
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserCreation() {
        // your test logic
    }
}

```
**The @ActiveProfiles("test") annotation tells Spring to load beans and properties from the test profile.**
3. Use @Profile("test") in Configuration or Beans (Optional)
```java
@Configuration
@Profile("test")
public class TestDataSourceConfig {
    
    @Bean
    public DataSource testDataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
    }
}

```
