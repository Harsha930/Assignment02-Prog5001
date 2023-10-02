/**
 * The Student class represents a student with their personal information and assignment marks.
 * It includes fields for the student's last name, first name, student ID, and assignment marks.
 * The class provides methods to retrieve these details and calculate the total marks for the student.
 * 
 * Name       -THOmmadura De Silva
 * Student Id -24077101
 * Date      -02/10/2023
 */
public class Student
{
    // instance variables
    private String lastName;    // The last name of the student
    private String firstName;   // The first name of the student
    private String studentID;   // The unique student ID
    private double[] marks;     // An array to store assignment marks

    /**
     * Constructs a new Student object with the specified personal information and assignment marks.
     *
     * @param lastName   The last name of the student.
     * @param firstName  The first name of the student.
     * @param studentID  The unique student ID.
     * @param marks      An array of assignment marks for the student.
     */
    public Student(String lastName, String firstName, String studentID, double[] marks) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentID = studentID;
        this.marks = marks;
    }
    
    /**
     * Gets the last name of the student.
     *
     * @return The last name of the student.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the first name of the student.
     *
     * @return The first name of the student.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the student's unique ID.
     *
     * @return The student's unique ID.
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Gets an array of assignment marks for the student.
     *
     * @return An array of assignment marks for the student.
     */
    public double[] getMarks() {
        return marks;
    }

    /**
     * Calculates the total marks for the student based on their assignment marks.
     *
     * @return The total marks achieved by the student.
     */
    public double calculateTotalMarks() {
        double totalMarks = 0;
        for (double mark : marks) {
            totalMarks += mark;
        }
        return totalMarks;
    }
}
