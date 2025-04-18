package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;

public class Estream5 {

    public static void main(String[] args) {
        List<String> names=Arrays.asList("Joydeb", "Aman","Roy","Suparna","Popo");
        names
            .stream()
            .sorted()
            .forEach(System.out::println);
    }
}