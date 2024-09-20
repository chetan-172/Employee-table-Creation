import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Employee_CreateTablesMySQL {
    public static void main(String[] args) {

        String DB_URL = "jdbc:mysql://localhost:3306/cdiv";
        String USER = "root";
        String PASSWORD = "BCA5C";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            Statement stmt = con.createStatement();

            String createDepartmentTable = "CREATE TABLE Department ("
                    + "Did INT PRIMARY KEY AUTO_INCREMENT, "
                    + "Dname VARCHAR(50)"
                    + ")";

            String createEmployeeTable = "CREATE TABLE Employee ("
                    + "Eid INT PRIMARY KEY AUTO_INCREMENT, "
                    + "Ename VARCHAR(50), "
                    + "Salary DECIMAL(10,2), "
                    + "Address VARCHAR(100), "
                    + "Did INT, "
                    + "FOREIGN KEY (Did) REFERENCES Department(Did)"
                    + ")";

            stmt.executeUpdate(createDepartmentTable);
            stmt.executeUpdate(createEmployeeTable);

            System.out.println("Tables created successfully!");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
