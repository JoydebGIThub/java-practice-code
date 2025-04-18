package streamApiPractice.stream;

import java.util.Arrays;
import java.util.List;

public class Estream10 {
    public static void main(String[] args) {
        List<Integer> num= Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum=num
            .stream()
            .reduce(0, (a,b)-> a+b);
        System.out.println("The result is: "+sum);

    }
}
