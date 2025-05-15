## @Primary, @Qualifier
- When we have 2 beans with same type then the spring will be confused which one need to be injected
```java
package com.springBoot.layerArchitecture.service;
import org.springframework.stereotype.Service;
@Service
public class CreditCardService implements PaymentService{
	@Override
	public void processPayment(double amount) {
		System.out.println("Credit Card payment service");	
	}
}
```

```java
package com.springBoot.layerArchitecture.service;
import org.springframework.stereotype.Service;
@Service
public class GPayService implements PaymentService{
	@Override
	public void processPayment(double amount) {
		System.out.println("GPayService payment service");	
	}
}
```
- field 
```java
package com.springBoot.layerArchitecture.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class CheckOutService {
	@Autowired
	@Qualifier("GPayService")
	PaymentService paymentService;	
	public void checkOutOrder(double amount) {
		paymentService.processPayment(amount);
		// TODO Auto-generated constructor stub
		System.out.println("Checkout order for amount: "+ amount);
	}
}
```
- constructor
```java
	private final PaymentService paymentService;
	
	public CheckOutService(@Qualifier("GPayService") PaymentService paymentService) {
		// TODO Auto-generated constructor stub
		this.paymentService= paymentService;
	}
```
**Qualifier annotation has precedence over Primary annotation**
#### When to use @Primary:
- Use `@Primary` when you want to designate one of the beans are the `default choice` for `dependency injection` when there are multiple beans of the same type. In this case, `DefaultPaymentService` is marked as the primary bean, so whenever Spring needs a paymentService and no `@Qualifier` is specified, it will inject `DefaultPaymentService`.

#### When to use @Qualifier
- Use `@Qualifier` when you need to specify a particular bean to be injected when there are multiple beans of the same type. It provides more `explicit control` over which been should be used in a particular context.















