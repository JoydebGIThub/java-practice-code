package coreJavaPractice;

public class Bbasic {
    public static void main(String[] args) {
        String day="Monday";
        int weekdays= 2;
        int num = switch (day) {
            case "MONDAY" -> 1;
            case "TUESDAY" -> 2;
            default -> 0;// default is more important here. In standard switch default not compulsory 
        };
        String days= switch (weekdays){
            case 1 -> "Monday";
            case 2 -> "Tusday";
            default -> "";
        };
        System.out.println(num);
        System.out.println(days);

    }
}
