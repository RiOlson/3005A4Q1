import java.sql.*;
import java.util.Scanner;

public class StudentDatabase {
    private static final String url = "jdbc:postgresql://localhost:5432/A4Q1";
    private static final String user = "USERNAME";
    private static final String password = "PASSWORD";

    /*
    getAllStudents returns every student in the student database.
     */
    public void getAllStudents(){
        // SQL Query to select all students
        String query = "SELECT * FROM students";

        try(
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet resultSet = pstmt.executeQuery()
                ){
            // Loop through each student
            while(resultSet.next()){
                // Get student values
                int studentId = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                Date enrollmentDate = resultSet.getDate("enrollment_date");

                // Print all student values
                System.out.println("Student ID: " + studentId);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("eMail: " + email);
                System.out.println("Enrollment Date: " + enrollmentDate);
                System.out.println("\n\n");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n\n");
        }
    }

    /*
    addStudent adds a student to the student database.
     */
    public void addStudent(String first_name, String last_name, String email, Date enrollment_date) {
        String query = "INSERT INTO students(first_name, last_name, email, enrollment_date) VALUES(?, ?, ?, ?)";

        try(
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(query);
        ){
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setDate(4, enrollment_date);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n\n");
        }
    }

    /*
    updateStudentEmail changes the e-mail of a given student by their student ID.
     */
    public void updateStudentEmail(int student_id, String new_email) {
        String query = "UPDATE students SET email=? WHERE student_id=?";

        try(
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(query);
        ){
            pstmt.setString(1, new_email);
            pstmt.setInt(2, student_id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n\n");
        }
    }

    /*
    deleteStudent deletes a student by their student ID.
     */
    public void deleteStudent(int student_id) {
        String query = "DELETE FROM students WHERE student_id=?";

        try(
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(query);
        ){
            pstmt.setInt(1, student_id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n\n");
        }
    }

    public static void main(String[] args) {
        StudentDatabase studentsDB = new StudentDatabase();
        Scanner scanner = new Scanner(System.in);

        // Provide user with action options.
        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Get all students");
            System.out.println("2. Add a student");
            System.out.println("3. Update a student's email");
            System.out.println("4. Delete a student");
            System.out.println("5. Exit");

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                // Get all students
                case 1:
                    studentsDB.getAllStudents();
                    break;

                // Add a student
                case 2:
                    System.out.println("Enter first name:");
                    String firstName = scanner.nextLine();

                    System.out.println("Enter last name:");
                    String lastName = scanner.nextLine();

                    System.out.println("Enter e-mail:");
                    String email = scanner.nextLine();

                    Date enrollmentDate = new Date(new java.util.Date().getTime());

                    studentsDB.addStudent(firstName, lastName, email, enrollmentDate);

                    break;

                // Update a student's e-mail
                case 3:
                    System.out.println("Enter student ID:");
                    int studentID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter new e-mail:");
                    String newEmail = scanner.nextLine();
                    studentsDB.updateStudentEmail(studentID, newEmail);

                    break;

                // Delete a student
                case 4:
                    System.out.println("Enter student ID:");
                    int studentToDelete = scanner.nextInt();
                    studentsDB.deleteStudent(studentToDelete);

                    break;

                // Exit the program
                case 5:
                    scanner.close();
                    System.exit(0);
            }
        }
    }
}