package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;

public class Estream11 {
    public static void main(String[] args) {
        List<Integer> nums= Arrays.asList(1, 1, 2, 7, 2, 1, 2, 8);
        nums
            .stream()
            .distinct()
            .forEach(System.out::println);
    }
}
