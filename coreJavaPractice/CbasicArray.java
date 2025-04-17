package coreJavaPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CbasicArray {
    public static void main(String[] args) {
        List<Integer> list1= new ArrayList<>();
        list1.add(2); 
        list1.add(2); 
        list1.add(1); 
        list1.add(4); 
        list1.add(2); 
        System.out.println(list1);

        List<Integer> list2= Arrays.asList(2, 4, 5, 2, 1, 2);
        // list2.add(2);//UnsupportedOperationException
        System.out.println(list2);

        List<Integer> list3 = List.of(1, 2,4, 5, 6, 7, 4);
        // list3.add(2); //don't support
        // list3.add(10); //don't support
        // list3.add(10); //don't support
        System.out.println(list3);

        List<Integer> list4= new ArrayList<>(Arrays.asList(2, 3, 5, 7, 3, 5, 3));
        list4.add(10);
        list4.add(10);
        list4.add(10);
        System.out.println(list4);
    }
}
