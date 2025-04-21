# Coding question:
## Find the factorial of a number using recursion and iteration
### Solution no: 1
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        Solution s= new Solution();
        int i=5;
        int result= s.factorial(i);
        System.out.println("Factorial of "+i+" is: "+result);
        
        
    }
}
class Solution{
    public int factorial(int x){
        if(x<0){
            return 0;
        }
        int result=1;
        while(x>0){
            result = result * x;
            // System.out.println(x);
            x--;
        }
        return result;
    }
}
```
### Output:
```
Factorial of 5 is: 120
```

### Solution no: 2
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        Solution s= new Solution();
        int i=5;
        int result= s.factorial(i);
        System.out.println("Factorial of "+i+" is: "+result);
        
        
    }
}
class Solution{
    public int factorial(int x){
        if(x<=0){
            return 1; 
        }
        return x * factorial(x-1);
    }
}
```