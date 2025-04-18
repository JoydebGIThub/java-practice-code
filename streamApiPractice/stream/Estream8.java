
package streamApiPractice.stream;

import java.util.Arrays;

public class Estream8 {

    public static void main(String[] args) {
        long count= Arrays.asList("one", "two", "three")
            .stream()
            .filter(n -> n.length()>3)
            .count();
        System.out.println("Words with length > 3: "+count);
    }
}