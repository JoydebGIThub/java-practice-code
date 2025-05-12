## Builder Design Pattern:
- In **factory design pattern** we give the responsibility of creating object to a method and we pass some parameter.
- While creating object when object contain many attribute there are many problem exists:
  1. we have to pass many arguments to create object.
  2. some parameters might be optional
  3. factory class takes all responsibility for creating object is **heavy** then all **complexity is the part of factory class**.

**So in builder pattern be create object step by step and finally return final object with desired values of attributes.**
- builder pattern help us to create **immutable object**

```java
import java.io.*;
class Main {
    public static void main(String[] args) {
       
        User user = new User.UserBuilder()
        .setEmailId("royr57334@gmail.com")
        .setUserId("UserId123")
        .setUserName("Joydeb")
        .build();
        System.out.println(user);
        
        User user2 = User.UserBuilder.builder()
        .setEmailId("royr57334@gmail.com")
        .setUserId("UserId123")
        // .setUserName("Joydeb")
        .build();
        System.out.println(user2);
    }
}
class User{
    private final String userId;
    private final String userName;
    private final String emailId;
    
    private User(UserBuilder builder){
        //intialize
        this.userId= builder.userId;
        this.userName = builder.userName;
        this.emailId = builder.emailId;
    }
    public String getUserId(){
        return userId;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public String getEmailId(){
        return emailId;
    }
    
    public String toString(){
        return this.userName+" : "+this.emailId+" : "+this.userId;
    }
    //inner class to create object
    static class UserBuilder{
        private String userId;
        private String userName;
        private String emailId;
        
        public UserBuilder(){
            
        }
        
        public static UserBuilder builder(){
            return new UserBuilder();
        }
        
        //Method chining
        public UserBuilder setUserId(String userId){
            this.userId = userId;
            return this;
        }
        public UserBuilder setUserName(String userName){
            this.userName = userName;
            return this;
        }
        public UserBuilder setEmailId(String emailId){
            this.emailId = emailId;
            return this;
        }
        
        public User build(){
            //creating User object
            User user = new User(this);
            return user;
        }
    }
}
```
### Output:
```
Joydeb : royr57334@gmail.com : UserId123
null : royr57334@gmail.com : UserId123
```
