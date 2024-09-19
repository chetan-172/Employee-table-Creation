import java.sql.*;

public class EmployeeInsertRowsOracle {
    public static void main(String[] args) {
        String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String USER = "SYSTEM";
        String PASSWORD = "BCA5C";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // Insert into Department
            String insertDept = "INSERT INTO Department (Did, Dname) VALUES (?, ?)";
            PreparedStatement pstmtDept = con.prepareStatement(insertDept);

            pstmtDept.setInt(1, 1);
            pstmtDept.setString(2, "HR");
            pstmtDept.addBatch();

            pstmtDept.setInt(1, 2);
            pstmtDept.setString(2, "IT");
            pstmtDept.addBatch();

            pstmtDept.setInt(1, 3);
            pstmtDept.setString(2, "Finance");
            pstmtDept.addBatch();

            pstmtDept.executeBatch();

            // Insert into Employee
            String insertEmp = "INSERT INTO Employee (Eid, Ename, Salary, Address, Did) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmtEmp = con.prepareStatement(insertEmp);

            pstmtEmp.setInt(1, 101);
            pstmtEmp.setString(2, "Alice");
            pstmtEmp.setDouble(3, 50000);
            pstmtEmp.setString(4, "New York");
            pstmtEmp.setInt(5, 1);
            pstmtEmp.addBatch();

            pstmtEmp.setInt(1, 102);
            pstmtEmp.setString(2, "Bob");
            pstmtEmp.setDouble(3, 55000);
            pstmtEmp.setString(4, "California");
            pstmtEmp.setInt(5, 2);
            pstmtEmp.addBatch();

            pstmtEmp.setInt(1, 103);
            pstmtEmp.setString(2, "Charlie");
            pstmtEmp.setDouble(3, 60000);
            pstmtEmp.setString(4, "Texas");
            pstmtEmp.setInt(5, 3);
            pstmtEmp.addBatch();

            pstmtEmp.executeBatch();

            System.out.println("Rows inserted in Oracle.");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
