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
