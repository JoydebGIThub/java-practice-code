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








