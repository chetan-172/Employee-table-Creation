import java.sql.*;
import java.util.Scanner;

public class PreparedStmtWithScanner {
    public static void main(String[] args) {
        String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String USER = "SYSTEM";
        String PASSWORD = "BCA5C";

        // Step 1: Register the driver class
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Step 2: Open DB connection 
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            if (con != null) {
                System.out.println("Connected to Oracle DB");

                // Prepare the SQL INSERT statement
                String insertSql = "INSERT INTO Student (sid, sname, city) VALUES (?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(insertSql);

                Scanner scanner = new Scanner(System.in);

                // Step 3: Start a loop to accept student details
                while (true) {
                    System.out.println("\nEnter 'Q' to quit or any other key to continue.");
                    String option = scanner.nextLine();

                    if (option.equalsIgnoreCase("Q")) {
                        System.out.println("Exiting...");
                        break;  // Exit the loop if the user enters 'Q'
                    }

                    // Accept student details from the user
                    System.out.print("Enter Student ID: ");
                    int sid = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Student Name: ");
                    String sname = scanner.nextLine();

                    System.out.print("Enter Student City: ");
                    String city = scanner.nextLine();

                    // Set the values for the placeholders
                    pstmt.setInt(1, sid);
                    pstmt.setString(2, sname);
                    pstmt.setString(3, city);

                    // Step 4: Execute the statement
                    int rowsAffected = pstmt.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted.");
                }

                // Step 5: Close the resources
                pstmt.close();
                con.close();
                scanner.close();

            } else {
                System.out.println("Failed to make connection");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
