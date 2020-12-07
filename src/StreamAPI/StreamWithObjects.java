package StreamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Student {
    private String name;
    private boolean local;

    public Student(String name, boolean local) {
        this.name = name;
        this.local = local;
    }

    public String getName() {
        return name;
    }

    public boolean isLocal(){return local;}
}

public class StreamWithObjects {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Adam", true));
        students.add(new Student("Sue", false));
        students.add(new Student("joe", true));

        long count = students.stream().filter(s->s.isLocal() == true).count();
        System.out.println("num local students: " + count);
        //map() is intermediate operation (each student is trancformed into a string)
        students.stream().filter(s -> (s.isLocal() == false)).map(Student::getName).forEach(System.out::println);

        String localNames = students.stream().filter(s -> (s.isLocal() == false)).map(Student::getName).collect(Collectors.joining(" "));
        System.out.println("localNames: " + localNames);
    }
}
