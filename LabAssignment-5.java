import java.io.*;
import java.util.*;

class StudentData {
    int rollNo;
    String name;
    String email;
    String course;
    double marks;

    public StudentData(int rollNo, String name, String email, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNo +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nCourse: " + course +
                "\nMarks: " + marks;
    }

    public String toCSV() {
        return rollNo + "," + name + "," + email + "," + course + "," + marks;
    }

    public static StudentData fromCSV(String line) {
        String[] p = line.split(",", -1);
        int r = Integer.parseInt(p[0].trim());
        String n = p.length > 1 ? p[1].trim() : "";
        String e = p.length > 2 ? p[2].trim() : "";
        String c = p.length > 3 ? p[3].trim() : "";
        double m = p.length > 4 ? Double.parseDouble(p[4].trim()) : 0.0;
        return new StudentData(r, n, e, c, m);
    }
}

class StudentFileHandler {
    private final File file;

    public StudentFileHandler(String filename) {
        this.file = new File(filename);
    }

    public List<StudentData> readStudents() {
        List<StudentData> list = new ArrayList<>();
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                try {
                    list.add(StudentData.fromCSV(line));
                } catch (Exception ex) {
                }
            }
        } catch (IOException e) {
            System.out.println("Error Reading File");
        }
        return list;
    }

    public void writeStudents(List<StudentData> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (StudentData s : students) {
                bw.write(s.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error Writing File");
        }
    }

    public String readLineAt(int index) {
        if (!file.exists()) return null;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            raf.seek(0);
            String line;
            int i = 0;
            while ((line = raf.readLine()) != null) {
                if (i == index) return new String(line.getBytes("ISO-8859-1"), "UTF-8");
                i++;
            }
        } catch (IOException e) {
        }
        return null;
    }
}

class StudentRecordController {
    private final ArrayList<StudentData> students;
    private final StudentFileHandler fileHandler;
    private final Scanner sc;

    public StudentRecordController(String filename, Scanner sc) {
        this.sc = sc;
        this.fileHandler = new StudentFileHandler(filename);
        this.students = new ArrayList<>(fileHandler.readStudents());

        if (!students.isEmpty()) {
            System.out.println("Loaded students from file:");
            for (StudentData s : students) {
                System.out.println(s);
            }
        }
    }

    public void addStudent() {
        try {
            System.out.print("Enter Roll No: ");
            int roll = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Enter Name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter Email: ");
            String email = sc.nextLine().trim();

            System.out.print("Enter Course: ");
            String course = sc.nextLine().trim();

            System.out.print("Enter Marks: ");
            double marks = Double.parseDouble(sc.nextLine().trim());

            students.add(new StudentData(roll, name, email, course, marks));
        } catch (Exception e) {
            System.out.println("Invalid Input!");
        }
    }

    public void viewAll() {
        for (StudentData s : students) System.out.println(s);
    }

    public void searchByName() {
        System.out.print("Enter Name to Search: ");
        String q = sc.nextLine().trim().toLowerCase();
        for (StudentData s : students) {
            if (s.name.toLowerCase().contains(q)) System.out.println(s);
        }
    }

    public void deleteByName() {
        System.out.print("Enter Name to Delete: ");
        String q = sc.nextLine().trim();
        boolean removed = students.removeIf(s -> s.name.equalsIgnoreCase(q));
        if (!removed) System.out.println("Name Not Found.");
    }

    public void sortByMarks() {
        students.sort((a, b) -> Double.compare(b.marks, a.marks));
        System.out.println("Sorted Student List by Marks:");
        for (StudentData s : students) System.out.println(s);
    }

    public void saveAndExit() {
        fileHandler.writeStudents(students);
        System.out.println("Data Saved.");
        fileHandler.readLineAt(0);
    }
}

public class LabAssignment4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentRecordController controller = new StudentRecordController("students.txt", sc);

        while (true) {
            System.out.println("===== Capstone Student Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Name");
            System.out.println("4. Delete by Name");
            System.out.println("5. Sort by Marks");
            System.out.println("6. Save and Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> controller.addStudent();
                case "2" -> controller.viewAll();
                case "3" -> controller.searchByName();
                case "4" -> controller.deleteByName();
                case "5" -> controller.sortByMarks();
                case "6" -> {
                    controller.saveAndExit();
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}
