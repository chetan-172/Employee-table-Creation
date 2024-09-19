import java.sql.*;

public class PreparedStmt {
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

                // Step 3: Create PreparedStatement
                String insertSQL = "INSERT INTO Student (sid, sname, city) VALUES (?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(insertSQL);

                // Setting the values for the placeholders (?)
                pstmt.setInt(1, 5); // sid
                pstmt.setString(2, "Sanjay"); // sname
                pstmt.setString(3, "Chennai"); // city

                // Step 4: Execute the prepared statement
                int rowsAffected = pstmt.executeUpdate();

                // Return the number of rows updated
                System.out.println(rowsAffected + " row(s) inserted.");

            } else {
                System.out.println("Failed to make connection.");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
