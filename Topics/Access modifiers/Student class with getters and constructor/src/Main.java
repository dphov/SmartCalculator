import java.util.*;

public class Main {
    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        String getName() {
            return name;
        }

        int getAge() {
            return age;
        }
    }

    static class Student extends Person {
        int studentID;

        Student(String name, int age, int studentID) {
            super(name, age);
            this.studentID = studentID;
        }

        int getStudentID() {
            return studentID;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int age = scanner.nextInt();
        int studentID = scanner.nextInt();

        // Write your code here
        Student s = new Student(name, age, studentID);
        System.out.println("Name: " + s.getName());
        System.out.println("Age: " + s.getAge());
        System.out.println("Student ID: " + s.getStudentID());
    }
}