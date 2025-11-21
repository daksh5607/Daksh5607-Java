import java.util.ArrayList;
import java.util.Scanner;

class Person {
    String name;
}

class Student extends Person {
    int rollNo;
    String course;
    double marks;
    char grade;

    Student(int rollNo, String name, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    void calculateGrade() {
        if (marks >= 90)
            grade = 'A';
        else if (marks >= 75)
            grade = 'B';
        else if (marks >= 60)
            grade = 'C';
        else
            grade = 'D';
    }

    void display() {
        System.out.println("Roll No : " + rollNo);
        System.out.println("Name    : " + name);
        System.out.println("Course  : " + course);
        System.out.println("Marks   : " + marks);
        System.out.println("Grade   : " + grade);
        System.out.println("-----------------------------");
    }
}

public class StudentRecordSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\n===== Student Record Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                sc.next(); // consume invalid input
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();

                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    sc.nextLine();

                    students.add(new Student(roll, name, course, marks));
                    System.out.println("Student Added Successfully!");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No student records available.");
                    } else {
                        System.out.println("\n===== All Student Records =====");
                        for (Student s : students)
                            s.display();
                    }
                    break;

                case 3:
                    System.out.println("Thank you! Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
