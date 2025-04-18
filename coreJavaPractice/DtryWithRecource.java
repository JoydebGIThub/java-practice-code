package coreJavaPractice;

import java.io.BufferedReader;
import java.io.FileReader;

public class DtryWithRecource {
    public static void main(String[] args) {
        String filePath="example.txt";

        try (BufferedReader br=new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine())!=null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("IOException: "+e.getMessage());
        }
    }
}
