# Interview Therory Question:
## Q: What is autoboxing 🧰 and unboxing 📤 in Java?
### Autoboxing:
```
Autoboxing 🧰 is the automatic conversion that the Java compiler makes between the "primitive types" and their corresponding "object wrapper classes".
```
- int to Integer
- double to Double
- boolean to Boolean
- and so on...
```
The compiler applies autoboxing when a primitive value is:
```
- Passed as an argument to a method that expects an object of the corresponding wrapper class.
- Assigned to a variable of the corresponding wrapper class.
```java
int primitiveInt = 10;
Integer wrapperInteger = primitiveInt; // Autobixing: int is automatically converted to Integer

double primitiveDouble = 3.14;
Double wrapperDouble = primitiveDouble; // Autoboxing: double is automatically converted to Double

List<Integer> integerList = new ArrayList<>();
integerList.add(5); //Autoboxing: int 5 is automatically converted to an Integer object  
```
### Unboxing:
```
Unboxing 📤 is the reverse process. It's the automatic conversion that the java compiler makes from an "object of a wrapper class" to its corresponding "primitive type" 
```
- Integer to int
- Double to double
- Boolean to boolean
- and so on...
```
The compiler applies unboxing 📤 when an object of a wrapper class is
```
- Passed as an argument to a method that expects a value of the corresponding primitive type.
- Assigned to a variable of the corresponding primitive type.
- Used in an operation where a primitive value is expected (arithmetic operations, comparisons)
```java
Integer i= 20;
int j=i;

Double a= Double.valueOf(2.71);
double b= a;

Integer num1= 5;
Integer num2= 6;
int sum = num1+num2; // Unboxing: Integer objects are unboxed to int for addition
```
