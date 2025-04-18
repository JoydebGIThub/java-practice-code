package streamApiPractice.stream;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Estream6 {
    public static void main(String[] args) {
        Optional<Integer> first= Arrays.asList(10, 20, 30, 40)
            .stream()
            .findFirst();
        first.ifPresent(System.out::println);

        List<Integer> list = Arrays.asList(1, 2, 3, 5, 6, 7, 8, 9);
        list    
            .stream()
            .findFirst()
            .ifPresent(System.out::println);
            
    }
}
