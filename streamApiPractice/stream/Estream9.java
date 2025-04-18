package streamApiPractice.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Estream9 {
    public static void main(String[] args) {
        List<List<String>> nested= Arrays.asList(
            Arrays.asList("a","b"),
            Arrays.asList("c","d")
        );
        List<String> flat= nested.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        System.out.println(flat);
        
        List<List<String>> map= nested.stream()
        .map(list -> new ArrayList<>(list))
        .collect(Collectors.toList());
        
        System.out.println(map);

    }
}
