package streamApiPractice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Student{
    int id;
    String name;
    String companany;
    Student(int id, String name, String companany){
        this.id=id;
        this.name=name;
        this.companany=companany;

    }
}

public class Astream {
    public static void main(String[] args) {
        List<Student> students= Arrays.asList(
            new Student(1, "Joydeb", "Accenture"),
            new Student(2, "Joydeb", "Infosys"),
            new Student(3, "Roushan", "Accenture"),
            new Student(4, "Roshan", "Accenture"),
            new Student(5, "Kapil", "LNT"),
            new Student(6, "Neha", "TCS")
        );
        // Map<String, List<Student>> list= students
        //     .stream()
        //     .collect(Collectors.groupingBy(emp-> emp.companany));
        // list.forEach((company, emp)->{
        //     System.out.println(company);
        //     emp.forEach(e->System.out.println(e.id+" "+e.name+" "+e.companany));
        //     System.out.println("..........................");
        // });

        students
            .stream()
            .collect(Collectors.groupingBy(emp -> emp.companany, Collectors.counting()))
            .forEach((company, count) -> System.out.println(company + " -> " + count));
    }
}
