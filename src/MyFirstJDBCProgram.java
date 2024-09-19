import java.sql.*;

public class MyFirstJDBCProgram {
    public static void main(String[] args)  {
        String DB_URL= "jdbc:oracle:thin:@localhost:1521:xe";
        String USER = "SYSTEM";
        String PASSWORD = "BCA5C";

        // Step 1: Register the driver class
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Step 2: Open DB connection 
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            if (con != null) {
                System.out.println("Connected to Oracle DB");
            } else {
                System.out.println("Failed to make connection to Oracle");
            }

            // Step 3: Create Statement 
            Statement stmt = con.createStatement();

            // Step 4: Check if table exists
            String checkTableSql = "SELECT table_name FROM user_tables WHERE table_name = 'STUDENT'";
            ResultSet rs = stmt.executeQuery(checkTableSql);

            if (!rs.next()) { // If table does not exist, create it
                String createSql = "CREATE TABLE Student (sid INT PRIMARY KEY, sname VARCHAR(20), city VARCHAR(20))";
                stmt.executeUpdate(createSql);
                System.out.println("Table 'Student' created.");
            } else {
                System.out.println("Table 'Student' already exists.");
            }

            // Step 5: Insert a new record with a different sid (sid = 4)
            // String insertSql = "INSERT INTO Student VALUES (4, 'Rahul', 'Delhi')";
            // int r = stmt.executeUpdate(insertSql);

            // if (r > 0) {
            //     System.out.println("New record inserted successfully with sid = 4.");
            // }

            // Step 6: Execute SELECT Statement
            String selectSql = "SELECT * FROM Student WHERE SID =4";
            rs = stmt.executeQuery(selectSql);

            // Step 7: Print the result
            while (rs.next()) {
                System.out.println("sid: " + rs.getInt("sid") + ", sname: " + rs.getString("sname") + ", city: " + rs.getString("city"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
