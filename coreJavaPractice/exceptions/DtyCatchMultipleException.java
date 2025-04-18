package coreJavaPractice.exceptions;

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
