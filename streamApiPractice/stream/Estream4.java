package streamApiPractice.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Person{
    String name;
    String city;

    Person(String name, String city){
        this.name=name;
        this.city=city;
    }
    public String getName(){
        return name;
    }
    public String getCity(){
        return city;
    }
}
public class Estream4 {

    public static void main(String[] args) {
        List<Person> people= new ArrayList<>(
            Arrays.asList(
                new Person("Joydeb", "Kolkata"),
                new Person("Suparna", "Bangalore"),
                new Person("Joy", "Delhi"),
                new Person("Popo", "Bangalore"),
                new Person("Bob", "Kolkata"),
                new Person("Roy", "Mumbai")
            )
        );
        Map<String, List<String>> groupingBycity= people.stream()
            .collect(Collectors.groupingBy(Person::getCity, 
            Collectors.mapping(Person::getName, Collectors.toList())));
        
        System.out.println(groupingBycity);

         people.stream()
            .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()))
            .forEach((s, l)->System.out.println(s+"-"+l));
    }
}