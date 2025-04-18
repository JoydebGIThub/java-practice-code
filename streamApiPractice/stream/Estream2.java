package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Estream2 {
    public static void main(String[] args) {
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 3, 2, 6, 5, 10);
        int result =num
            .stream()
            .filter(n -> n %2 == 0)
            .mapToInt(Integer:: intValue)
            .sum();
        System.out.println("Sum: "+result);

        //alternative way
        int result2= num
            .stream()
            .filter(n-> n%2==0)
            .collect(Collectors.summingInt(Integer::intValue));
        System.out.println(result2);
    }
}
