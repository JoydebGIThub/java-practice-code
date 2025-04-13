package streamApiPractice;

import java.util.List;
import java.util.stream.Collectors;

public class Bstream {
    public static void main(String[] args) {
        List<Integer> list= List.of(1, 8, 8, 58, 23, 9, 8, 1, 2, 58);
        list
            .stream()
            .collect(Collectors.groupingBy(a->a, Collectors.counting()))
            .forEach((key, value)-> System.out.println(key+"-"+value));;
    }
}
