## What is Profiling?
- We have a application running on local and connect with the devDB
- Now again we deploy our code in another environment where the QA connection will be different for the DB
- There are so many other configurations, which are different in different environment

### How to handle different configuartion in different environment
- here the **Profiling** comes
- We can create different `.properties` for different environment
- the name of the file will be `application-{profile1}.properties` like :: **application-prod.properties**

### How to set profile
- At application startup we can tell spring to pick specific application.properties file
- using spring.profiles.active configaration
```properties
usernames = Username
password = Password
spring.profiles.active= prod
```
- if the prod file is not find then the `usernames` and `password` will be pick from the `parent file` **application.properties**

- So, every time we need to change the active from the code if we need to extranalize this then we can change the active file on run time
- So for it we need to configure the profile dynamically

### How to configure profile config dynamically
- 2 ways
1. while application startup using command
   - `mvn spring-boot:run -Dspring-boot:run.profiles=dev`
   - we can open the `Run configuration` and put `dev` in the `Profile` --> Apply --> Run
2. Add profile in the `pom.xml`
   - and run using `mvn spring-boot:run -Pproduction`
```xml
</build>
	<profiles>
		<profile>
			<id>local</id>
			<properties>
				<spring-boot.run.profiles>dev</spring-boot.run.profiles>
			</properties>
		</profile>
		<profile>
			<id>production</id>
			<properties>
				<spring-boot.run.profiles>prod</spring-boot.run.profiles>
			</properties>
		</profile>
	</profiles>
		
</project>
```

## @Profile Annotation
- Using Profile annotation we can tell spring boot, to create a bean only when particular profile is set
- Its primary work is tell the spring to inject the bean or not inject the bean
```java
package com.springBoot.layerArchitecture.DBConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
@Component
@Profile("dev")
public class MySQLConnection {	
	@Value("${usernames}")
	String username;
	@Value("${password}")
	String password;
	@PostConstruct
	public void init() {
		System.out.println("DB Connection init");
		System.out.println("username: "+ username +" | password: "+password);
	}
	public MySQLConnection() {
		// TODO Auto-generated constructor stub
		System.out.println("MySQLConnection init");
	}
}
```

**if we write spring.profiles.active= prod, dev**
- then both will be active but only the configuration is pick from `dev`



