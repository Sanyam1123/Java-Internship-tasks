import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Student class representing individual students
class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student { Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade + " }";
    }
}

// Student Management System class
class StudentManagementSystem {
    private ArrayList<Student> students;
    private final String filePath = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public void removeStudent(String rollNumber) {
        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                studentToRemove = student;
                break;
            }
        }
        if (studentToRemove != null) {
            students.remove(studentToRemove);
            saveToFile();
        }
    }

    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
                students = (ArrayList<Student>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading data: " + e.getMessage());
            }
        }
    }
}

// Main class to run the program
public class StudentManagementApp {

    public static void main(String[] args) {
        // Uncomment one of the following to choose the interface
        runGUI();
        // runGUI();
    }

    private static void runConsoleUI() {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n==== Student Management System ====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addStudent(sms, scanner);
                    break;
                case 2:
                    removeStudent(sms, scanner);
                    break;
                case 3:
                    searchStudent(sms, scanner);
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);

        scanner.close();
    }

    private static void addStudent(StudentManagementSystem sms, Scanner scanner) {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Roll Number: ");
        String rollNumber = scanner.nextLine();

        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();

        if (!name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty()) {
            Student student = new Student(name, rollNumber, grade);
            sms.addStudent(student);
            System.out.println("Student added successfully.");
        } else {
            System.out.println("All fields are required.");
        }
    }

    private static void removeStudent(StudentManagementSystem sms, Scanner scanner) {
        System.out.print("Enter Roll Number of the student to remove: ");
        String rollNumber = scanner.nextLine();
        sms.removeStudent(rollNumber);
    }

    private static void searchStudent(StudentManagementSystem sms, Scanner scanner) {
        System.out.print("Enter Roll Number of the student to search: ");
        String rollNumber = scanner.nextLine();
        Student student = sms.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student Found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void runGUI() {
        SwingUtilities.invokeLater(() -> {
            StudentManagementSystem sms = new StudentManagementSystem();
            JFrame frame = new JFrame("Student Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new FlowLayout());

            // Input fields
            JTextField nameField = new JTextField(10);
            JTextField rollField = new JTextField(10);
            JTextField gradeField = new JTextField(10);
            JButton addButton = new JButton("Add Student");
            JButton displayButton = new JButton("Display All Students");

            // Add components to frame
            frame.add(new JLabel("Name:"));
            frame.add(nameField);
            frame.add(new JLabel("Roll Number:"));
            frame.add(rollField);
            frame.add(new JLabel("Grade:"));
            frame.add(gradeField);
            frame.add(addButton);
            frame.add(displayButton);

            // Add action listeners
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String roll = rollField.getText();
                    String grade = gradeField.getText();
                    if (!name.isEmpty() && !roll.isEmpty() && !grade.isEmpty()) {
                        sms.addStudent(new Student(name, roll, grade));
                        JOptionPane.showMessageDialog(frame, "Student added successfully.");
                        nameField.setText("");
                        rollField.setText("");
                        gradeField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "All fields are required.");
                    }
                }
            });

            displayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StringBuilder allStudents = new StringBuilder();
                    for (Student student : sms.getStudents()) {
                        allStudents.append(student.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, allStudents.toString());
                }
            });
            
            frame.setVisible(true);
            // Center the frame on the screen
            frame.setLocationRelativeTo(null);
        });
    }
}
