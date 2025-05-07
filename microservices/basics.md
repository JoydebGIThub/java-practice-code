## Disadvantages of Monolithic Pattern:
- Tight coupling of services:
  - If one service is down other services also get affected.
  - If one service is planned to redeploy other also get affected.
- Scaling problem
- Technology locking (we need to develop everything is same technology like if use java then only jave needs to use)
- Codebase become complex as project scale.

## What is Microservices?
- Software Design Pattern where software is composed of small independent services that communicate over well-defined APIs.

## Desadvantages of Microservices?
1. Increased Complexity:
   - Operational Overhead: Managing a distributed system with many independent services is inherently more complex than managing a single monolithic application. This includes deployment, monitoring, logging, and troubleshooting.
   - Inter-service Communication: Establishing efficient and reliable communication between services (often over a network) adds complexity. You need to consider network latency, message formats, and potential communication failures.
   - Distributed Transactions: Ensuring data consistency across multiple independent databases can be challenging. Implementing distributed transaction management or eventual consistency patterns adds significant complexity.
   - Testing Complexity: Testing a microservices application involves testing individual services and their interactions, requiring more sophisticated testing strategies and tools.
2. Performance Overhead:
   - Network Latency: Communication between services over a network introduces latency, which can impact overall application performance compared to in-process calls in a monolith.
   - Serialization/Deserialization: Data exchanged between services often needs to be serialized and deserialized, adding processing overhead.
3. Development and Deployment Challenges:
   - Increased Development Effort: Breaking down a monolithic application into microservices requires significant architectural planning and development effort.
   - Distributed Development: Coordinating development across multiple teams working on different services can be challenging and requires clear communication and well-defined contracts.
   - Complex Deployment: Deploying and managing numerous independent services requires robust automation and orchestration tools (e.g., Kubernetes).
   - Versioning and Compatibility: Managing different versions of services and ensuring backward compatibility can become complicated as the system evolves.
4. Data Management Challenges:
   - Data Consistency: Maintaining data consistency across multiple databases owned by different services is a significant challenge.
   - Data Duplication: Services may need to duplicate some data to avoid excessive inter-service communication, leading to potential data inconsistencies.
5. Security Concerns:
   - Increased Attack Surface: With more services and network communication, the attack surface of the application increases.
   - Securing Inter-service Communication: Implementing secure communication protocols and authentication/authorization mechanisms between services is crucial but adds complexity.

## Microservice Communication.
- We can use **RestClient** offers a more modern API for synchronous HTTP access. For asynchronous and streaming scenario, consider the reactive **WebClient**.
- For calling api we also can use **OpenFeign**
- OpenFeign is a part of Spring Cloud
### OpenFeign: 
- Declarative Rest client OpenFeign creates a dynamic implementation of an interface decorated with JAX-RS or spring mvc annotation
#### To use the spring cloud we need to add the dependencyManagement in between dependencies and build:
```xml
<dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>${spring-cloud.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
```
**and add cloud version in properties**
```xml
<properties>
    <java.version>17</java.version>
    <spring-cloud.version>2024.0.1</spring-cloud.version>
</properties>
```
**now add the OpenFeign dependency**
```xml
<dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
#### Create a client service in the service
- 1st we need to add a dummy class which data is not store in the database but we can use the reference in the client service
```java
@Getter
@Setter
@AllArgsConstructer
@NoArgsConstructer
public class Question{
  private Long questionId;
  private String question;
  private Long quizId;
}
```
- 2nd create the interface
```java
@FeignClient(url="http://localhost:8082", value="Question-service")
public interface QuestionClient{
  @GetMapping("/question/quiz/{quizId}")
  List<Question> getQuestionOfQuiz(@PathVariable Long quizId);
}
```

- 3rd enable the OpenFeign
```java
@SpringBootApplication
@EnableFeignClients
public class QuizServiceApplications{

}
```
- 4th modify the classes
```java
@Entity
@Getter
@Setter
@AllArgsConstructer
@NoArgsConstructer
public class Quiz{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;

  //not to store in the database
  transient private List<Question> questions;

}

```
```java
@Service
public class QuizImp implements QuizService{
  QuizRepository repo;

  private QuestionClient question;

  public QuizImp(QuizRepository repo, QuestionClient question){
    this.question= question;
    this.repo= repo;
  }

  public List<Quiz> get(){
    List<Quiz> quiz = repo.findAll();

    List<Quiz> newList = quiz.stream().map(quiz -> {
      quiz.setQuestions(question.getQuestionOfQuiz(quiz.getid()));
      retrun quiz;
    }).toList();
    return newList;
  }
}
```

## Service Registry / Discovery Server
- **Service Discovery** is a mechanism used in microservices to automatically detect and keep track of the network locations (IP addresses and ports) of service instances in a system.
  - Problem it solves: In microservices, services often scale up/down dynamically, and their network locations change. Manually configuring the address of each service becomes impractical.
  - Solution: Service Discovery automates this process, allowing services to register themselves and discover each other without hardcoding their locations.
- A **Discovery Server** (also known as a Service Registry) is a central component that keeps track of all available services and their instances.
  - Services register themselves with the Discovery Server when they start.
  - Other services query the Discovery Server to find the location of a needed service.
- Register all microservice
- Manage Instances
- Load balancing manage

**It act like a server and rest of the service act like a client**
**We use Eureka server and Eureka client to create the server and client. They are the part of Netflix**

### For creating the server we need:
- Dependencis like: Spring web, Eureka Server
```xml
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>
```

#### For not registering itself as a client we need to disable it as client:
- application.properties
```properties
server.port = 8761
spring.application.name= Service-Registry

eureka.instance.hostname=localhost

#disable as client
eureka.client.register-with-eureka = false
eureka.client.fetch-registry = false
```

#### Enable the server
```java
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication{
}
```

## New register the rest of the services as eureka client:
- Dependency we need: Eureka client
```xml
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
```

**In previous version of spring boot 3 we need to enable the service as client but now it auto configure by own**
- In pervious version we enable:
```java
@SpringBootApplication
@EnableFeignClients
public class QuizServiceApplication {
}
```
- now to connect to the server:
```properties
eureka.instance.client.serverUrl.defaultZone = http://localhost:8761/eureka/
```
### Configure the load balancing
- In previous version the loadbalance was included in eureka now we need to add the dependency
```xml
<dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-loadbalancer</artifactId>
    </dependency>
```
- In a microservices architecture using **Service Discovery**, the load balancer dependency should be **added to the client service** — the service that is making the call to another service.
- If you're using Spring Cloud LoadBalancer (modern and Ribbon replacement): This allows your client service to use annotations like @LoadBalanced and automatically resolve service names from a discovery server like Eureka.
```java
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}

// Using service name registered with Eureka
ResponseEntity<String> response = restTemplate.getForEntity("http://ORDER-SERVICE/api/orders", String.class);

```

**In the above code, "ORDER-SERVICE" is the service name registered in the Discovery Server (like Eureka), and the load balancer will resolve it to an actual instance IP/port.**

#### Configer the QuestionClient:
- Here we want to communicate with another service
```java
@FeignClient(name="Question-Service")
public interface QuestionClient{

}
```
### To check the load balanceing we create a different instance of Question Service:
- create a jar of QuestionService
  - change the port
  - QuestionService -> LifeCycle -> package
  - target -> QuestionService-0.01-SNAPShot -> rightclick -> Open -> explore -> Open on Terminal
  - java -jar .\QuestionService-0.01-SNAPShot.jar

## 🧰 What is an API Gateway?
- An API Gateway is a server that acts as a single entry point into a system of microservices. It receives requests from clients, routes them to the appropriate microservices, aggregates responses, and sends them back to the client.

### The dependencies we need:
- gateway, eureka client, actuator
```xml
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-gateway-mvc</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
```
1. Gateway: Provides a simple, yet effective way to route to APIs and provide cross cutting concerns to them such as security, monitoring/metrics and resiliency.
2. Actuator: Support built in (or custom) endpoints that let you monitor and manage your application, such as application health, metrics, sessions, env, etc.

#### routes / predicat configuration
- application.properties
```properties
server.port = 8083
spring.application.name = Api-gateway

#routes / predicat configuration
spring.cloudgateway.routes[0].id= Quiz-Service
spring.cloudgateway.routes[0].uri= lb:// Quiz-Service
spring.cloudgateway.routes[0].predicates[0]=path = /quiz/**, /quiz-test/**
```
- also we can configur it like:
```properties
spring.application.name=api-gateway
server.port=8765

spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true
```


