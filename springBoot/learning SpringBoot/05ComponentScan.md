## @ComponentScan
- it is a major element of the spring application, by using the @ComponentScan the spring will find the `Bean` inside the application

## How application finds the Beans / pojo inside the application
- Component Scanning will find the pojo which needs to be converted into Bean and all the beans inside the application

- Application will start scanning the whole project for beans, so which package it should scan will defined by `Component Scanning`
- Where need to be looking for bean in defined by **Component Scanning**
- In Spring boot application the `@ComponentScan` is a part of the `@SpringBootApplication`. So, here we don't need to explicitly define the `@ComponetScan` here 

- Let assugme we have 2 package `layerArchitecture` and `ecomassistant` and in `layerArchitecture` package have a main class which have the `@SpringBootApplication` so if we run the application the `ecomassistant` package's class will not be consider as `bean` of the main package.
- So we can use `@ComponentScan` to `scan` the package explicitly
```java
package com.springBoot.layerArchitecture;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages ="com.springBoot.ecomassistant")
public class LayerArchitectureApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext= SpringApplication.run(LayerArchitectureApplication.class, args);
		System.out.println("Application COntext" + applicationContext.getBean("productService"));
	}
}
```
- But after that we fatch some issue the Component of the `layerArchitecture` package will not be scan. Beacaue after useing `@ComponentScan(basePackages ="com.springBoot.ecomassistant")` the Spring will only scan the `ecomassistant` package so we need to add the `layerArchitecture` package path also
```java
package com.springBoot.layerArchitecture;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
// @ComponentScan(basePackages = {"com.springBoot.ecomassistant", "com.springBoot.layerArchitecture"})
@ComponentScan(basePackages = "com.springBoot")
public class LayerArchitectureApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext= SpringApplication.run(LayerArchitectureApplication.class, args);
		System.out.println("Application COntext" + applicationContext.getBean("productService"));
	}
}
```

### 2nd way to configer the @ComponentScan for two packages:
- `@ComponentScan` should be used along with `@Configuration` annotation
- Without the `@Configuration` annotation the Component Scanning will not be working properly.
```java
package com.springBoot.layerArchitecture.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@ComponentScan(basePackages = "com.springBoot.ecomassistant")
public class EcomConfig {
}
```

## ComponentScan argument
- basePackage
- exclusions / excludeFilters
  - if we want to `exclude` any **class, package, and a class which is annotated with certain annotation** that we can do it by **excludeFilters**
```java
package com.springBoot.layerArchitecture.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import com.springBoot.ecomassistant.service.DeprecatedYUtilityService;
@Configuration
@ComponentScan(
		basePackages = "com.springBoot.ecomassistant",
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {DeprecatedYUtilityService.class})
		)
public class EcomConfig {
}
```
### FilterType:
- ANNOTATION: Filter candidates marked with a given annotation
- ASSIGNABLE_TYPE: Filter candidates assignable to a given type
- ASPECTJ: Filter candidates matching a given AspectJ type pattern expression.
- REGEX: Filter candidates matching a given regex pattern.
- CUSTOM: Filter candidates using a given custom
