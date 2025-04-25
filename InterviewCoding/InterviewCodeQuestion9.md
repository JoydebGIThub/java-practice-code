# Given a list of integers, find the pair whose sum is closes to zero;
## Solution no: 1
```java
import java.util.*;
class Main {
    public static int[] findClosestPair(int[] arr) {
        if (arr == null || arr.length < 2) {
            System.out.println("No such pair found (ialid input).");
            return new int[0]; // Or throw an exception for invalid input
        }
        Arrays.sort(arr);
            
        
        int left = 0;
        int right = arr.length-1;
        int closestSum = Integer.MAX_VALUE;
        int[] closestPair = new int[2];
        while (left < right) {
            int currentSum = arr[left] + arr[right];
            if (Math.abs(currentSum) < Math.abs(closestSum)) {
                closestSum = currentSum;
                closestPair[0] = arr[left];
                closestPair[1] = arr[right];
            }
            if (currentSum < 0) {
                left++; // Need a larger sum, so move the left pointer to a larger value
            } else if (currentSum > 0) {
                right--; // Need a smaller sum, so move the right pointer to a smaller value
            }else {
                // If the sum is exactly zero, we've found the closest possible pair
                closestPair[0] = arr[left];
                closestPair[1] = arr[right];
                return closestPair;
            }
        }
        
        return closestPair;
    }
    
    public static void main(String[] args) {
        
        int[] numbers = {1, 60, -10, 70, -80, 85};
        int[] pair = findClosestPair(numbers);

        if (pair.length == 2) {
            System.out.println("The pair whose sum is closest to zero is: (" + pair[0] + ", " + pair[1] + ")");
        } else {
            System.out.println("No such pair found (invalid input).");
        }
        
        int[] numbers2 = {-5, 2, -3, 4, 1};
        int[] pair2 = findClosestPair(numbers2);
        if (pair2.length == 2) {
            System.out.println("The pair whose sum is closest to zero is: (" + pair2[0] + ", " + pair2[1] + ")");
        } else {
            System.out.println("No such pair found (invalid input).");
        }
        
        int[] numbers3 = {0};
        int[] pair3 = findClosestPair(numbers3);
        if (pair3.length == 2) {
            System.out.println("The pair whose sum is closest to zero is: (" + pair3[0] + ", " + pair3[1] + ")");
        } else {
            System.out.println("No such pair found (invalid input).");
        }
    }
}
```
## Solution no: 2
```java

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClosestPairToZeroStream {

    public static Optional<int[]> findClosestPairUsingStream(List<Integer> numbers) {
        if (numbers == null || numbers.size() < 2) {
            return Optional.empty();
        }

        return IntStream.range(0, numbers.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, numbers.size())
                        .mapToObj(j -> new int[]{numbers.get(i), numbers.get(j)}))
                .min((pair1, pair2) -> {
                    int sum1 = Math.abs(pair1[0] + pair1[1]);
                    int sum2 = Math.abs(pair2[0] + pair2[1]);
                    return Integer.compare(sum1, sum2);
                });
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 60, -10, 70, -80, 85);
        Optional<int[]> closestPair = findClosestPairUsingStream(numbers);

        closestPair.ifPresentOrElse(
                pair -> System.out.println("The pair whose sum is closest to zero is: (" + pair[0] + ", " + pair[1] + ")"),
                () -> System.out.println("No such pair found (invalid input).")
        );

        List<Integer> numbers2 = Arrays.asList(-5, 2, -3, 4, -1);
        Optional<int[]> closestPair2 = findClosestPairUsingStream(numbers2);
        closestPair2.ifPresent(pair -> System.out.println("The pair whose sum is closest to zero is: (" + pair[0] + ", " + pair[1] + ")"));

        List<Integer> numbers3 = Arrays.asList(0, 0);
        Optional<int[]> closestPair3 = findClosestPairUsingStream(numbers3);
        closestPair3.ifPresent(pair -> System.out.println("The pair whose sum is closest to zero is: (" + pair[0] + ", " + pair[1] + ")"));
    }
}
```
