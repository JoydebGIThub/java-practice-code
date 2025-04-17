package streamApiPractice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Dstream {
    public static void main(String[] args) {
        List<String> list= Arrays.asList("Joydeb", "Suparna","Popo");
        list
            .stream()
            .filter(v -> v.length() > 4)
            .collect(Collectors.groupingBy(v -> v.length()))
            .forEach((key, value)-> System.out.println(key+": "+value));
    }
}
