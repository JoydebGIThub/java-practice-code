package streamApiPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Cstream {
    public static void main(String[] args) {
        List<Integer> list= new ArrayList<>(Arrays.asList(1, 3, 2, 4, 2, 1, 6, 7, 8));
        Optional<Integer> value= list
            .stream()
            .filter(v -> v == 2)
            .findFirst();
        System.err.println(value.get());
    }
}
