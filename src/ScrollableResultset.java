import java.sql.*;

public class ScrollableResultset {
    public static void main(String[] args) {
        String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String USER = "SYSTEM";
        String PASSWORD = "BCA5C";

        try {
            // Step 1: Register the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Step 2: Open DB connection 
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            if (con != null) {
                System.out.println("Connected to Oracle DB");

                // Step 3: Create scrollable Statement object
                Statement stmt = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);

                // Step 4: Execute a SELECT query and get the scrollable ResultSet
                String selectSql = "SELECT * FROM Student";
                ResultSet rs = stmt.executeQuery(selectSql);

                // Step 5: Move forward through the ResultSet using a while loop
                System.out.println("Iterating forward through the ResultSet:");
                while (rs.next()) {
                    int sid = rs.getInt("sid");
                    String sname = rs.getString("sname");
                    String city = rs.getString("city");

                    System.out.println("Student ID: " + sid + ", Name: " + sname + ", City: " + city);
                }

                // Optionally, move backward to demonstrate scrollable behavior
                System.out.println("\nIterating backward through the ResultSet:");
                while (rs.previous()) {
                    int sid = rs.getInt("sid");
                    String sname = rs.getString("sname");
                    String city = rs.getString("city");

                    System.out.println("Student ID: " + sid + ", Name: " + sname + ", City: " + city);
                }

                // Step 6: Close resources
                rs.close();
                stmt.close();
                con.close();

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
