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
