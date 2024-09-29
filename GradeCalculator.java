import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of subjects
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();
        
        // Input: Marks obtained in each subject
        double[] marks = new double[numberOfSubjects];
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextDouble();
            // Validate marks input
            while (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Marks should be between 0 and 100. Please enter again.");
                marks[i] = scanner.nextDouble();
            }
        }

        // Calculate Total Marks
        double totalMarks = calculateTotal(marks);
        // Calculate Average Percentage
        double averagePercentage = (totalMarks / (numberOfSubjects * 100)) * 100;
        // Calculate Grade
        char grade = calculateGrade(averagePercentage);

        // Display Results
        System.out.println("\n==== Results ====");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    // Method to calculate total marks
    private static double calculateTotal(double[] marks) {
        double total = 0;
        for (double mark : marks) {
            total += mark;
        }
        return total;
    }

    // Method to calculate grade based on average percentage
    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
