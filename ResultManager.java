import java.util.*;

class InvalidMarksException extends Exception {
    InvalidMarksException(String msg) {
        super(msg);
    }
}

class Student {
    int rollNumber;
    String studentName;
    int[] marks = new int[3];

    Student(int rollNumber, String studentName, int[] marks) {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.marks = marks;
    }

    void validateMarks() throws InvalidMarksException {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 0 || marks[i] > 100) {
                throw new InvalidMarksException(
                        "Invalid marks for subject " + (i + 1) + ": " + marks[i]);
            }
        }
    }

    double calculateAverage() {
        return (marks[0] + marks[1] + marks[2]) / 3.0;
    }

    void displayResult() {
        double avg = calculateAverage();
        String result = (avg >= 40) ? "Pass" : "Fail";

        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Student Name: " + studentName);
        System.out.println("Marks: " + marks[0] + " " + marks[1] + " " + marks[2]);
        System.out.println("Average: " + avg);
        System.out.println("Result: " + result);
    }
}

public class ResultManager {

    Student[] students = new Student[50];
    int count = 0;
    Scanner sc = new Scanner(System.in);

    void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            }

            Student s = new Student(roll, name, marks);
            s.validateMarks();

            students[count++] = s;
            System.out.println("Student added successfully.");

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Input type mismatch.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void showStudentDetails() {
        try {
            System.out.print("Enter Roll Number to search: ");
            int roll = sc.nextInt();

            for (int i = 0; i < count; i++) {
                if (students[i].rollNumber == roll) {
                    students[i].displayResult();
                    return;
                }
            }
            System.out.println("Student not found.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void mainMenu() {
        try {
            while (true) {
                System.out.println("\n===== Student Result Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. Show Student Details");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();

                if (choice == 1) addStudent();
                else if (choice == 2) showStudentDetails();
                else if (choice == 3) {
                    System.out.println("Exiting program. Thank you!");
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        } finally {
            sc.close();
            System.out.println("Scanner closed.");
        }
    }

    public static void main(String[] args) {
        new ResultManager().mainMenu();
    }
}
