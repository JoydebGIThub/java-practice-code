package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;

public class Estream {
    public static void main(String[] args) {
        List<String> names= Arrays.asList("Joydeb", "Suparna", "Popo", "Bob", "Joy");
        names
            .stream()
            .filter(name-> name.startsWith("J"))
            .map(String::toUpperCase)
            .forEach(System.out::println);
        names
            .stream()
            .filter(n -> n.startsWith("J"))
            .map(m->m.toUpperCase())
            .forEach(e -> System.out.println(e));
    }
}
