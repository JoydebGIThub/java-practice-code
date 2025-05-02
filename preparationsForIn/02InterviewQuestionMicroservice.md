## What is microservice?
- A microservice is an architectural style that structures an application as a collection of small, independent services, each built around a specific business capability. These services communicate with each other over a network, often using lightweight protocols like HTTP with RESTful APIs.   
************************************************************************************
## Let assume there is 2 microservices how they will communicate with each other?
let's assume we have two microservices, let's call them Service A and Service B. They need to interact to fulfill a certain business function. Here are the common ways they can communicate with each other:
1. Synchronous Communication (Request/Response):
   -
   - RESTful APIs (HTTP/HTTPS): This is the most common approach for synchronous communication in microservices.
     - Service A makes an HTTP request (e.g., GET, POST, PUT, DELETE) to a specific endpoint exposed by Service B.
     - Service B processes the request and sends back an HTTP response, which Service A waits for.
     - Pros: Simple to understand and implement for basic interactions, widely adopted, uses standard HTTP protocols and tools.
     - Cons: Can lead to tight coupling if not designed carefully, prone to latency issues (Service A is blocked waiting for Service B), can lead to cascading failures if Service B is slow or unavailable.
```
[Service A] ---> (HTTP Request) ---> [Service B]
[Service A] <--- (HTTP Response) <--- [Service B]
```
2. Asynchronous Communication (Message/Event-Driven):
   -
   - Message Queues (e.g., RabbitMQ, Kafka, AWS SQS):
     - Service A sends a message to a queue.
     - Service B subscribes to that queue and processes the messages as they arrive.
     - Pros: Loose coupling (senders and receivers don't need to know about each other), improved fault tolerance (messages can be queued if the receiver is down), better scalability (services can process messages at their own pace), supports event-driven architectures.
     - Cons: Increased complexity in setting up and managing the message broker, eventual consistency (changes might not be immediately reflected in all services), requires handling message formats and potential delivery failures.
```
[Service A] ---> (Message) ---> [Message Queue]
                                    |
                                    v
[Service B] <--- (Message) <--- [Message Queue]
```
*********************************************************************************
## What is service discovery and what is the flow of it?
Ans: Service Discovery is the process by which services in a microservices architecture automatically locate and communicate with each other without needing hardcoded configurations of their network locations (IP addresses and ports). In a dynamic environment where services can be frequently created, destroyed, and scaled, service discovery is crucial for maintaining communication and ensuring the overall system's health and resilience.
To enable Eureka service discovery in a Spring Boot application, you typically need to perform the following steps for both the Eureka Server (the registry) and the Eureka Clients (the microservices that will register with the server):
- Enable Eureka Server: Add the @EnableEurekaServer annotation to your main Spring Boot application class.
```java
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

  @SpringBootApplication
  @EnableEurekaServer
  public class EurekaServerApplication {
      public static void main(String[] args) {
          SpringApplication.run(EurekaServerApplication.class, args);
      }
  }
  ```
- Configure Eureka Server Properties: In your application.properties
 ```properties
  spring.application.name=eureka-server
  server.port=8761
  eureka.client.register-with-eureka=false
  eureka.client.fetch-registry=false
  ```
- Enable Eureka Client in Microservices: Add the @EnableEurekaClient or @EnableDiscoveryClient annotation to your main Spring Boot application class of each microservice. @EnableEurekaClient is specific to Eureka, while @EnableDiscoveryClient is a more general annotation for Spring Cloud Discovery implementations (including Eureka).
```java
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  import org.springframework.cloud.netflix.eureka.EnableEurekaClient; // Or @EnableDiscoveryClient

  @SpringBootApplication
  @EnableEurekaClient
  public class MyMicroserviceApplication {
      public static void main(String[] args) {
          SpringApplication.run(MyMicroserviceApplication.class, args);
      }
  }
  ```
**********************************************************************************












