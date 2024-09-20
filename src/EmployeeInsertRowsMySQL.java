import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EmployeeInsertRowsMySQL {
    public static void main(String[] args) {
        String DB_URL = "jdbc:mysql://localhost:3306/cdiv";
        String USER = "root";
        String PASSWORD = "BCA5C";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String insertDepartment = "INSERT INTO Department (Dname) VALUES (?)";
            String insertEmployee = "INSERT INTO Employee (Ename, Salary, Address, Did) VALUES (?, ?, ?, ?)";

            PreparedStatement deptStmt = con.prepareStatement(insertDepartment);
            PreparedStatement empStmt = con.prepareStatement(insertEmployee);

            // Insert Departments
            deptStmt.setString(1, "HR");
            deptStmt.executeUpdate();
            deptStmt.setString(1, "Engineering");
            deptStmt.executeUpdate();
            deptStmt.setString(1, "Finance");
            deptStmt.executeUpdate();

            // Insert Employees
            empStmt.setString(1, "John Doe");
            empStmt.setDouble(2, 60000);
            empStmt.setString(3, "123 Street, City");
            empStmt.setInt(4, 1);  // HR department
            empStmt.executeUpdate();

            empStmt.setString(1, "Jane Smith");
            empStmt.setDouble(2, 75000);
            empStmt.setString(3, "456 Avenue, City");
            empStmt.setInt(4, 2);  // Engineering department
            empStmt.executeUpdate();

            empStmt.setString(1, "Jim Brown");
            empStmt.setDouble(2, 50000);
            empStmt.setString(3, "789 Boulevard, City");
            empStmt.setInt(4, 3);  // Finance department
            empStmt.executeUpdate();

            System.out.println("Rows inserted successfully!");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
