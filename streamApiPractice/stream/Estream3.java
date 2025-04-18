package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Estream3 {
    public static void main(String[] args) {
        List<String> names= Arrays.asList("Joydeb", "Joy","Suparna", "Bob");
        List<String> filtered= names
                                .stream()
                                .filter(n->n.contains("J"))
                                .collect(Collectors.toList());
        System.out.println(filtered);
    }
}
