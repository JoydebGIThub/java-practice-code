## Customizing the Nature of Bean in Lifecycle
- After creating the `bean` we are initializing something, we are customizing the nature of bean and before destorying we are releasing something / performing some task.

## What are Custom Actions While Bean Creation?
- let the bean perform certain actions upon initialization and destruction of your beans.
- Perform Actions After Bean Creation
- Perform Actions Before Destroying Bean

### Initializing Bean
- After the `Bean` is created the method will be called.
- So after the `Bean` creation I can perform some operation by using the method
```java
package com.springBoot.layerArchitecture.service;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.springBoot.layerArchitecture.repository.ProductRepository;
@Service
public class ProductService implements InitializingBean{
	private final ProductRepository productRepository;
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository=productRepository;
		System.out.println("Product Service");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("after properties set");
	}
}
```
#### Output:
```
Product Service
after properties set
```

### DisposableBean
- Perform some operation before destorying the `bean`
```java
package com.springBoot.layerArchitecture.service;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.springBoot.layerArchitecture.repository.ProductRepository;
@Service
public class ProductService implements InitializingBean, DisposableBean{
	private final ProductRepository productRepository;
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository=productRepository;
		System.out.println("Product Service");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("after properties set");
	}
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Distory");
	}
}
```
- This `Distory` will not print until the application is stop.
- So, we can explicitly stop the application to check the result
```java
package com.springBoot.layerArchitecture;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
public class LayerArchitectureApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext= SpringApplication.run(LayerArchitectureApplication.class, args);
		System.out.println("Application COntext" + applicationContext.getBean("productService"));
		applicationContext.close();
	}
}
```

### Instead of using the InitializingBean, DisposableBean we can use @PostConstruct and @PreDestroy
- Modern way to do InitializingBean and DisposableBean
```java
package com.springBoot.layerArchitecture.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springBoot.layerArchitecture.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
@Service
public class ProductService{
	private final ProductRepository productRepository;
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository=productRepository;
		System.out.println("Product Service");
	}
	@PostConstruct
	public void init() {
		System.out.println("Init steps after product service created");
	}	
	@PreDestroy
	public void destry() {
		System.out.println("Destroy steps after product service created");
	}
}
```
