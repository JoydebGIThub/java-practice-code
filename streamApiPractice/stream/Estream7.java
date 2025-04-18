package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Estream7 {
    public static void main(String[] args) {
        List<String> names= Arrays.asList("Joydeb","Suparna","Popo");
        names.stream()
            .map(String::toUpperCase)
            .forEach(System.out::println);

        List<String> namesInUpper= names
            .stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println(namesInUpper);
    }
}
