# try catch Exception
## Checked Exception

```
    Checked on compile time
```

### IOException
#### Q: File not found
```java
    package coreJavaPractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DtryCatch {
    public static void main(String[] args) {
        try {
            readFile("example.txt");
        } catch (IOException e) {
            // e.printStackTrace(); //it will give the whole issue in a stack
            System.out.println("IO exception Caught: "+e.getMessage());//this will give the error message only
        }
        
    }

    public static void readFile(String fileName) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine())!=null) {
            System.out.println(line);
        }
        reader.close();

    }
}

```
### Output:
```
IO exception Caught: example.txt (The system cannot find the file specified)
```
### Explain some part
```text
    => e.printStackTrace(); :- give all the error in a stack
    => System.out.println("IO exception: "+e.getMessage()); :- give the error through a message only

```

### Custom Checked Exception
#### Q: check the age is at least 18

```java
package coreJavaPractice;

class InvalidAgeException extends Exception{
    public InvalidAgeException(String message){
        super(message);
    }
}

public class DtryCatchCustom {
    public static void main(String[] args) {
        try {
            validAge(16);
        } catch (InvalidAgeException e) {
            // e.printStackTrace();
            System.out.println("Custom exception: "+e.getMessage());
        }
        
    }

    public static void validAge(int age) throws InvalidAgeException{
        if (age<18) {
            throw new InvalidAgeException("Age must be at least 18");
        }
        System.out.println("Access Granted");
    }
}

```

### Output:
```
    Custom exception: Age must be at least 18
```
### InterruptedException
#### Q: If the Thread.sleep got interapted
```java
package coreJavaPractice;

public class DtryCatchThread {
    public static void main(String[] args) {
        try {
            System.out.println("Sleeping for 2 second");
            Thread.sleep(2000);
            System.out.println("Awake now");
        } catch (InterruptedException e) {
           System.out.println("InterruptedException Catch: "+e.getMessage());
        }
    }
}

```

### ClassNotFoundException
#### Q: Working with Class.forName() to load the class
```java
package coreJavaPractice;

class Testing{
    static{
        System.out.println("Exception testing");
    }
}

public class DtryCatchClassNotFound {
    public static void main(String[] args) {
        try {
            Class.forName("java.util.ArrayLists");
            System.out.println("Class loaded properly");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException catch: "+e.getMessage());
        }
    }
}

```
### Output:
```
    ClassNotFoundException catch: java.util.ArrayLists
```

#### Q: Multiple checked Exception in one Method
```java
    package coreJavaPractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DtyCatchMultipleException {
    public static void main(String[] args) {
        try {
            handleOperation();
        } catch (ClassNotFoundException | IOException  e) {
            System.out.println("Exception caught "+e.getClass().getSimpleName() +" - " +e.getMessage());
        }
    }

    public static void handleOperation() throws IOException, ClassNotFoundException{
        readFile("example.txt");
        classCheck();
    }
    public static void readFile(String filename) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        reader.close();
    }
    public static void classCheck() throws ClassNotFoundException{
        Class.forName("java.util.ArrayLists");
    }
}

```

## Unchecked Exception

```
    Unchecked exception handle on Runtime time
```
### ArithmeticException
#### Q: Devided by Zero
```java
package coreJavaPractice;

public class DtryCatchArithmetic {
    public static void main(String[] args) {
        try {
            System.out.println("The result: "+checked(10, 0));

        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
    public static int checked(int a, int b) throws ArithmeticException{
        int result = a/b;
        System.out.println("Result is: "+result);
        return result;
    }
}

```
### Output: 
```
    / by zero
```
#### Q: pass 10 and 0 as float parameter in int return type method and find the return in different situations

```java
    package coreJavaPractice;

public class DtryCatchArithmetic2 {
    public static void main(String[] args) {
        int value = divide(10, 0);
        System.out.println(value);
    }   
    public static int divide(float a, float b){
        
        try {
            return (int) (a/b);
        } catch (Exception e) {
            return 20;
        } finally{
            return 30;
        }
    } 
}

```
### Output:
```
    30
```

#### Explanation:
```
    try {
            return (int) (a/b);
        } catch (Exception e) {
            return 20;
        } finally{
            return 30;
        }

    it will return 30 by "Overriding" the catch return
```
```
    try {
            return (int) (a/b);
        } catch (Exception e) {
            return 20;
        }
     no matter what we return in the catch it will return "2147483647"
```
```
in main method i pass:
=> int value = divide(10, 0);

but the divide method defined as:
=> public static int divide(float a, float b)

You're dividing 10f / 0f, which is a float division by zero, and that does NOT throw an exception in Java!
**Insted**
10.0f / 0.0f = Infinity

**when you cast Infinity to an int, you get:**
(int) Float.POSITIVE_INFINITY == 2147483647
```

```
    So if:
    public static int divide(int a, int b){
        
        try {
            return a/b;
        } catch (Exception e) {
            return 299;
        }
    } 
    then it retun 299
```

### ArrayIndexOutOfBoundsException
```java
    package coreJavaPractice;

public class DtryCatchArrayIndexOutofBound {
    public static void main(String[] args) {
        int[] array={1, 2, 3};

        try {
            System.out.println(array[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caugth ArrayIndexOutOfBoundsException: "+e.getMessage());
        }

    }
}

```
### Output:
```
Caugth ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 3 
```

### NullPointerException
```java
package coreJavaPractice;

public class DtryCatchNullpointer {
    public static void main(String[] args) {
        String str=null;
        try {
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: "+e.getMessage());
        }
    }
}

```
### Output:
```
Caught NullPointerException: Cannot invoke "String.length()" because "str" is null
```

### NumberFormatException
```java
package coreJavaPractice;

public class DtryCatchNumberFormat {
    public static void main(String[] args) {
        String str="abc";

        try {
            int number = Integer.parseInt(str);
            System.out.println("Number: "+number);
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException: "+e.getMessage());
        }
    }
}

```

### Output:
```
Caught NumberFormatException: For input string: "abc"
```

### StringIndexOutOfBoundsException
```java
package coreJavaPractice;

public class DtryCatchStringIndex {
    public static void main(String[] args) {
        String text="Java";

        try {
            System.out.println(text.charAt(10));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Caught StringIndexOutOfBoundsException: "+e.getMessage());
        }
    }
}

```

### Output:
```
Caught StringIndexOutOfBoundsException: Index 10 out of bounds for length 4
```

### Custom unChecked Exception
```java
package coreJavaPractice;

class MyUnCheckedException extends RuntimeException{
    public MyUnCheckedException(String message){
        super(message);
    }
}

public class DtryCatchMyUnchecked {
    public static void main(String[] args) {
        try {
            validMarks(-4);
        } catch (MyUnCheckedException e) {
            System.out.println("Caught Custom Unchecked Exception: "+e.getMessage());
        }
    }

    public static void validMarks(int marks) throws MyUnCheckedException{
        if(marks<0){
            throw new MyUnCheckedException("Marks can't be negative");
        }
        System.out.println("Valid marks: "+marks);
    }
}

```
### Output:
```
Caught Custom Unchecked Exception: Marks can't be negative
```