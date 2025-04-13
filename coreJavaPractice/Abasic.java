package coreJavaPractice;

class Car{
    static{
        System.out.println("Static Block");
    }

    {
        System.out.println("Instance Block");
    }
}

public class Abasic{
    public static void main(String[] args) throws Exception{
        // Class.forName("coreJavaPractice.Car");
       Class<?> carClass= Class.forName("coreJavaPractice.Car");
       carClass.getDeclaredConstructor().newInstance();
    }
}