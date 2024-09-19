import java.sql.*;

public class EmployeeCreateTablesOracle {
    public static void main(String[] args) {
        String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String USER = "SYSTEM";
        String PASSWORD = "BCA5C";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            Statement stmt = con.createStatement();
            
            // Create Department Table
            String createDept = "CREATE TABLE Department (Did INT PRIMARY KEY, Dname VARCHAR(20))";
            stmt.executeUpdate(createDept);
            System.out.println("Department table created in Oracle.");

            // Create Employee Table
            String createEmp = "CREATE TABLE Employee (Eid INT PRIMARY KEY, Ename VARCHAR(20), Salary DECIMAL(10, 2), Address VARCHAR(50), Did INT, "
                    + "FOREIGN KEY (Did) REFERENCES Department(Did))";
            stmt.executeUpdate(createEmp);
            System.out.println("Employee table created in Oracle.");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
