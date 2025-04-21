# Coding question:
## Check if a number is a palindrome?
### Solution no: 1 not good for -1221
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        int num= 1221;
        StringBuilder s= new StringBuilder();
        s.append(num);
        s.reverse();
        String s1= s.toString();
        int num2= Integer.parseInt(s1);
        
        if(num==num2){
            System.out.println("It's a palindrome number");
        }else{
            System.out.println("Not a palindrome number");
        }
        System.out.println(num2);
        
    }
}
```
### Output:
```
It's a palindrome number
1221
```

## Solution no: 2
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        int num= -1221;
        StringBuilder s= new StringBuilder();
        s.append(num);
        s.reverse();
        String s1= s.toString();
        String s2= String.valueOf(num);
        // int num2= Integer.parseInt(s1);
        
        if(s1.equals(s2)){
            System.out.println("It's a palindrome number");
        }else{
            System.out.println("Not a palindrome number");
        }
        System.out.println(s1);
        
    }
}
```
### Solution no: 3
```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        
        if(Solution.isPalindrome(-121)){
            System.out.println("It's a palindrome number");
        }else{
            System.out.println("Not a palindrome number");
        }
        
    }
}
class Solution{
    public static boolean isPalindrome(int x){
        int y=x;
        if(x<0){
            return false;
        }
        int result=0;
        while(x!=0){
            int num= x%10;
            result *=10;
            result +=num;
            x= x/10;
            // System.out.println(num+" "+x+" "+result);
        }
        return y==result;
    }
}
```