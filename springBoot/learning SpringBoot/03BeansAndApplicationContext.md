## What is Bean?
- Bean is a `Java Object`. Whan a Java Object or Pojo is manage by the Spring application is refered as `Bean` inside the application.
- Bean can have multiple instances

## What is Spring IOC container?
- The IOC container manages all the Bean inside the Spring application along with the dependency of those beans.
- Bean creation, deletion, updation is manage by this container.
- All the beans are stored inside the container.
- The Spring IOC container is kind of `teminology`
- The actual implementation is `ApplicationContext`
- **ApplicationContext** will be the actual object inside the application which will `store or refer` to all the `beans`. In generec it is refer as IOC container.
- There is one more implementation of IOC container is `BeanFactories`

## 2 ways to creating Bean
1. @Component
```java
package com.springBoot.layerArchitecture.service;
import org.springframework.stereotype.Component;
@Component
public class ProductService {
	public ProductService() {
		System.out.print("Product Service");
	}
}
```

2. @Configuration & @Bean
```java
package com.springBoot.layerArchitecture.service;
public interface PaymentService {
	void processPayment(double amount);
}

package com.springBoot.layerArchitecture.service;
public class GPayService implements PaymentService{
	@Override
	public void processPayment(double amount) {
		System.out.println("Gpay payment service");		
	}
}

package com.springBoot.layerArchitecture.service;
public class CreditCardService implements PaymentService{
	@Override
	public void processPayment(double amount) {
		System.out.println("Credit Card payment service");		
	}
}

package com.springBoot.layerArchitecture.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.springBoot.layerArchitecture.service.CreditCardService;
import com.springBoot.layerArchitecture.service.GPayService;
import com.springBoot.layerArchitecture.service.PaymentService;
@Configuration
public class PaymentConfig {
	@Bean
	public PaymentService paymentService() {
		return new CreditCardService();
	}	
	@Bean
	public PaymentService gpayPaymentService() {
		return new GPayService();
	}
}
```


## Check the present Bean in our project:
1. using actuator
   - 1st add dependency in pom.xml
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
  - 2nd enable the actuator
```properties
management.endpoints.web.exposure.include=*
```
  - 3rd hit the url
```url
http://localhost:8080/actuator/beans
```

2. Using object of application context
```java
package com.springBoot.layerArchitecture;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@SpringBootApplication
public class LayerArchitectureApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext= SpringApplication.run(LayerArchitectureApplication.class, args);
		System.out.println("Application COntext" + applicationContext.getBean("productService"));
	}
}
```





