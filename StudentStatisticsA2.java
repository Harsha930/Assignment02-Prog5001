import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Write a description of class StudentStatisticsA2 here.
 *
 * Name       -THOmmadura De Silva
 * Student Id -24077101
 * Date      -02/10/2023
 */
public class StudentStatisticsA2
{
    // instance variables
    private static String unitName = "";
    private static ArrayList<Student> students = new ArrayList<>();    
    private static final String COMMENT_PREFIX = "//";

    /**
     * The main method of the program. It presents a menu system to the user for various operations on student data.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);        

        // F5: Menu System
        boolean exit = false;
        boolean fileRead = false;
        while (!exit) {
            System.out.println("\n-------------------Menu-------------------");
            System.out.println("1. Read CSV File (F1)");
            System.out.println("2. Print Students Total Marks (F2)");
            System.out.println("3. Print Students Below Threshold (F3)");
            System.out.println("4. Print Top 5 Students (F4)");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = input.nextInt();
            input.nextLine();  

            switch (choice) {
                case 1:
                    // F1: Read data from CSV file
                    System.out.print("Enter the CSV file name: ");
                    String fileName = input.nextLine();
                    readCSVFile(fileName);
                    fileRead = true;
                    break;
                case 2:
                    if(fileRead){
                        // F2: Calculate total marks
                        calculateTotalMarks();                        
                    }
                    else{
                        System.out.println("Read data from file first.");
                    }
                    break;
                case 3:
                    if(fileRead){
                        // F3: Print Students Below Threshold
                        System.out.print("Enter the threshold: ");
                        double threshold = input.nextDouble();
                        printStudentsBelowThreshold(threshold);
                    }
                    else{
                        System.out.println("Read data from file first.");
                    }
                    break;
                case 4:
                    if(fileRead){
                        // F4: Print Top 5 Students
                        printTopAndBottomStudents();
                    }
                    else{
                        System.out.println("Read data from file first.");
                    }
                    break;
                case 5:
                    // Exit
                    exit = true;
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        input.close();
    }
    
    /**
     * Reads student data from a comma delimeted CSV file and populates the 'students' list.
     *
     * @param fileName The name of the CSV file to read.
     */
    public static void readCSVFile(String fileName){        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                if (unitName.isEmpty() && lineNumber == 0) {
                    // Extract and store the unit name
                    unitName = line.substring(line.indexOf(':') + 1).trim();
                    System.out.println("Unit Name: " + unitName);
                } else if (lineNumber != 1 && !line.startsWith(COMMENT_PREFIX)) {
                    // Process student data
                    String[] parts = line.split(",");
                    String lastName = parts[0];
                    String firstName = parts[1];
                    String studentID = parts[2];
                    double[] marks = new double[3];
                    for (int i = 0; i < 3; i++) {
                        if (i + 3 < parts.length && !parts[i + 3].isEmpty()) {
                            marks[i] = Double.parseDouble(parts[i + 3]);
                        } else {
                            marks[i] = 0; // Assign 0 if the mark is missing or empty
                        }
                    }
                    Student student = new Student(lastName, firstName, studentID, marks);
                    students.add(student);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }
    }

    /**
     * Calculates and prints the total marks for all students.
     */
    public static void calculateTotalMarks() {
        System.out.println("Total Marks for All Students");
        for (Student student : students) {
            System.out.println(student.getFirstName() + " " + student.getLastName() + " (ID: " + student.getStudentID() +
                    ", Total Marks: " + student.calculateTotalMarks() + ")");
        }
    }

    /**
     * Prints the list of students below a given threshold.
     *
     * @param threshold The threshold value for total marks.
     */
    public static void printStudentsBelowThreshold(double threshold) {
        System.out.println("Students Below Threshold (" + threshold + "):");
        for (Student student : students) {
            if (student.calculateTotalMarks() < threshold) {
                System.out.println(student.getFirstName() + " " + student.getLastName() + " (ID: " + student.getStudentID() + 
                ", Total Marks: " + student.calculateTotalMarks() + ")");
            }
        }
    }

   /**
     * Prints the top and bottom students based on their total marks.
    */
    public static void printTopAndBottomStudents() {
        int n = students.size();
        
        // Sort students based on total marks
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < n - 1; i++) {
                if (students.get(i).calculateTotalMarks() < students.get(i + 1).calculateTotalMarks()) {
                    // Swap students at index i and i+1
                    Student temp = students.get(i);
                    students.set(i, students.get(i + 1));
                    students.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    
        // Print top 5 students with the lowest total marks
        System.out.println("Top " + 5 + " Students:");
        for (int i = 0; i < 5; i++) {
            Student student = students.get(i);
            System.out.println(student.getFirstName() + " " + student.getLastName() + " (ID: " + student.getStudentID() +
                    ", Total Marks: " + student.calculateTotalMarks() + ")");
        }
        
        // Print bottom 5 students with the highest total marks
        System.out.println("\nBottom " + 5 + " Students:");
        for (int i = n - 1; i >= n - 5; i--) {
            Student student = students.get(i);
            System.out.println(student.getFirstName() + " " + student.getLastName() + " (ID: " + student.getStudentID() +
                    ", Total Marks: " + student.calculateTotalMarks() + ")");
        }
    }

}
