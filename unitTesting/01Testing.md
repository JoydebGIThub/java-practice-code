## 1st step to create a Test class:
They syntax of the TestClass name should be "ClassNameTest" inside the Test package
## 2nd step to create a meaningful java method:
```java
	void myFirstTest() {
		System.out.println("my first unit test");
	}
```
## 3rd step to make the method to Test we add @Test annotation over the method and the import package should be "org.junit.jupiter.api.Test":
```java
package com.joydeb.quiz_service.service;
import org.junit.jupiter.api.Test;
public class QuizServiceTest {

	@Test
	void myFirstTest() {
		System.out.println("my first unit test");
	}
}

```
**@Test annotation is very important which will enable the particular method to be exequted for us**
## 4th step to call the testing method we create an object of that class and call the method to test the value
- so in Test class we should not create a real object we should create a **mock** object, so there the mocketo comes. We create the mocketo object by using @Mock over the Object class.
- Then we need to enable the Mock, the mock will give us a dummy/ mock object so the nullPointerException will be vanish:
```java
package com.joydeb.quiz_service.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {
	@Mock
	QuizService service;	
	@Test
	void getQuizQuestionsSuccessfully() {
		System.out.println("my first unit test");		
		service.getQuizQuestions(101); // Without mock object we get the NullPointerException for this
	}	
}
```

## 5th steps: to inject all the dependency the object need we need to bring that object to the test add the @Mock to create a dummy object of that dependency and then add @InjectMocks annotation over the previous object so that it can get all the dependency it needs if that object present in the same class
```java
package com.joydeb.quiz_service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.joydeb.quiz_service.dao.QuizDao;
@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {
	@Mock
	QuizDao dao;
	@InjectMocks
	QuizService service;
	@Test
	void getQuizQuestionsSuccessfully() {
		System.out.println("my first unit test");	
		service.getQuizQuestions(101); // Without mock object we get the NullPointerException for this
	}	
}

```
## 6th step: mock the db calls and get the data so that we can test it:
```java
@Test
	void getQuizQuestionsSuccessfully() {
		System.out.println("my first unit test");
		Mockito.when(dao.findById(101)).thenReturn(null);
		service.getQuizQuestions(101); // Without mock object we get the NullPointerException for this
		
		//test product == matched product
	}
```

## 7th step to check the expected result and the actual result we use Assertions.assertEquals(expected, actual)
```java
@Test
	void getQuizQuestionsSuccessfully() {
		System.out.println("my first unit test");
		Quiz q = new Quiz();
		//set some value
		Mockito.when(dao.save(q)).thenReturn(q);
		Quiz quiz =service.createQuizs(q); // Without mock object we get the NullPointerException for this
		
		//test product == matched product
		Assertions.assertEquals(q.getId(), quiz.getId());
	}
```
## If I want my expected result not to null then use can use:
```java
//test product == matched product
		Assertions.assertNotNull(quiz);
		Assertions.assertEquals(q.getId(), quiz.getId());
```

