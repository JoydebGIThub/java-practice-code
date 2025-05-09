# Interview Therory Question:
## Q: What is the difference between Stream and Parallel Stream?
- Imagine we have a list of items, and we want to perform some operations on them - maybe filter out certain items, transform them in some way, or calculate a summary.
Traditionally, we might use loops (for, while) to iterates through the list and do these things step by step.
- Java "Streams", introduced in Java 8, provide a more elegant and powerful way to process collections of data. They allow you to express these operations as a "pipeline of fuctions" that operate on the elements of the stream.

### Sequential Streams: One Step at a Time:
- Think of a "sequential stream" like a single worker on an assembly line. Each item in the collection goes through the line, and the worker performs each operation on that item before moving to the next.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
public class SequentialStream{
  public static void main(String[] arr){
    List<Integer> numbers= Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    //Create a sequential stream from the list
    Stream<Integer> sequentialStream= numbers.stream();
    //Define a pipeline of operations
    sequentialStream.filter(n -> n%2 !=0) // Intermediate operation
                    .map(n -> n*2) // Intermediate operation
                    .forEach(result-> System.out.print(result+" ")); // Terminal operation
    System.out.println();
  }
}
```
#### Output:
```
[2, 6, 10, 14, 18]
```
#### Breakdown:
1. numbers.stream(): This line creates a sequential stream from the numbers list. The data will be processed in the order it appears in the list.
2. .filter(n -> n%2 !=0): This is an "intermediate operation". It takes a "Predicate"(a function that returns a boolean) and keeps only the elements for which the predicate is true (in this case, odd numbers). The stream now contains [1, 3, 5, 7, 9].
3. .map(n-> n*2): Another intermediate operation. It takes a "Function" (a function that transforms one element to another) and applies it to each element in the stream. Here, each odd number is multiplied by 2. The stream now contains [2, 6, 10, 14, 18].
4. .forEach(result -> System.out.print(result + " ")): This is a terminal operation. It consumes the stream and performs an action for each element. Here, it prints each number followed by a space.

#### Key Point:
- The operations in a sequential stream are executed one after the other, on a single thread.

### Parallel Streams: Many workers Doing the Job Simultaneously:
- Now, imagine a "parallel stream" as having multiple workers on that assembly line. The work (processing the items) is divided among these workers, and they perform their tasks concurrently. This can significantly speed up processing for large datasets and computationally intensive operations.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
public class ParalleStream{
  public static void main(String[] arr){
    List<Integer> numbers= Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    //Create a sequential stream from the list
    Stream<Integer> parallelStream= numbers.parallelStream();
    //Define a pipeline of operations
    parallelStream.filter(n -> n%2 !=0) 
                    .map(n -> n*2) 
                    .forEach(result-> System.out.print(result+" "));
    System.out.println();
  }
}
```
#### Output: (order might vary):
```
10 18 6 14 2
```
#### Breakdown:
1. numbers.parallelStream(): This is the crucial difference. It creates a "parallel stream" from the "numbers" list. The Java runtime will try to divide the processing of the elements across multiple threads available in the system's thread pool(typically the fork-join common pool).
2. .filter(...), .map(...), .forEach(...): The intermediate and terminal operations remain the same in terms of what they do. However, their execution happens in parallel.

#### Important Considerations for Parallel Streams:
- Performance Gains: Parallel streams can lead to significant performance imporvements, especially when:
  - You have a "large amount" of data to process.
  - The operations you're performing on each element are computationally intensive.
  - Your system has multiple CPU cores.
- Overhead: There's an overhead associated with "splitting the data", "managing threads" and "combining the results" in parallel streams. For very small datasets or very simple operations, this overhead might outweigh the benefits, and a sequential stream could be faster.
- Stateful Operations: Be cautious with "stateful intermediate operations (like distinct() or sorted())" in parallel streams, as their behavior might become unpredicatable or less efficient due to the parallel processing order.
- Side Effects: Avoid operations with "side effects" (like modifying external variables or performing I/O within forEach without proper synchronization) is parallel streams, as the order of execution is not guaranteed, leading to potential race conditions and unexpected results.
- Ordering: As seen in the previous example with "forEach", the order of processing and the final output might not be the same as the original order of elements in a parallel stram. If you need to preserve the order, you might need to use "forEachOrdered()", but this can reduce the performance benefites of parallelism.
***
***
## .map() and .flatMap()
### .map(): One to One Transformation:
- The ".map()" operation is used to transform "each element" in a stream into a "new element". It takes a "Function" as an argument, which is applied to each element of the stream. The result is a new stream containing the "transformed elements". There's a direct "one-to-one" mapping:➡️ one input element produces one output element.
- Imagine a machine that takes individual apples as input and outputs peeled apples. For each apple you put in, you get one peeled apple out.

```java
import java.util.*;
public class MapExample{
  public static void main(String[] a){
    List<String> words = Arrays.asList("hello","world","java");

    //Use .map() to transform each word to its length
    Stream<Integer> wordLengths = words.stream()
                                        .map(String::length); //Method reference to String's length();
    //print the lengths
    wordLengths.forEach(length->System.out.print(length+" "));
    System.out.println();

    //Converting numbers to their squares
    List<Integer> numbers= Arrays.asList(1, 2, 3, 4, 5);
    Stream<Integer> squares = numbers.stream().map(n -> n*n); //Lambda expression for squaring
    squares.forEach(n->System.out.println(n+" "));
    System.out.println();
  }
```
#### Breakdown:
1. words.stream().map(String::length):
   - We create a stream of "String" object from the "words" list.
   - The ".map()" operation takes the "String::length" method reference. For each "String" in the stream, the "length()" method is called, and the result(an Integer representing the length) becomes the element in the new stream "wordLengths".

#### Key Characteristics of .map():
- One-to-one transformation: Each element in the input stream is transformed into exactly one element in the output stream.
- Type transformation: The type of the elements in the output stream can be different from the type of the elements in the input stream (String to Integer).
- Return a new stream: ".map()" is an intermediate operation, so it returns a new stream with the transformed elements.

### .flatMap(): One-to-Many Transformation and Flattening:
- The .flatmap() operation is more powerful. It's used when each element in the original stream can be transformed into zero or more elements (typically by producing another stream or a collection). ".flatMap()" then flattens these resulting streams into single stream.
- Imagine a machine that takes boxes of apples as input. For each box, it opens it and takes out all the individual apples, not boxes of apples.

```java
import java.util.*;
public class FlatMap{
  public static void main(String args[]){
    List<List<String>> listOfLists = Arrays.asList(
        Arrays.asList("a","b"),
        Arrays.asList("c","d", "e"),
        Arrays.asList("f")
    );
    // Use .flatMap() to flatten the list of lists into a single stram of strings
    Stream<String> flattenedStream = listOfLists.stream().flatMap(List::stream);
    flattenedStream.forEach(s-> System.out.print(s+" "));
    System.out.println();

    //Another example:
    List<String> words = Arrays.asList("Hello","world");
    Stream<Character> characters= words.stream().flatMap(word -> word.chars() // returns an IntStream of character codes
                                                  .mapToObj(c -> (char) c)); //convert to Stream<Charcter>
    characters.forEach(ch -> System.out.print(ch+" "));
  }
}
```
#### Breakdown:
1. listOfLists.stream().flatMap(List::stream):
   - We have a stream of "List<String>" (a list of lists of strings).
   - The ".flatMap()" operation takes "List::stream" as the function. For each inner "List<String>", the "stream()" method is called, which produces as "Stream<String>"
   - ".flatMap()" then takes all these individual "Stream<String>" objects and "flattens" them into a single "Stream<String>". The result is a stream containing "a", "b", "c", "d","e","f".
2. words.stream().flatMap(word -> word.chars().mapToObj(c-> (char)c)):
   - We have a stream of "String" objects.
   - For each "word", the lambda expression does the following:
     - word.char(): Returns an IntStream of the character code of the characters in the word.
     - .mapToObj(c -> (char) c): Converts each integer character code back to a "Character" object, resulting in a "Stream<Character>".
   - .flatMap() then flattens these "Stream<Character>" objects from each word into a single "Stream<Character>" containing all the individual characters.
