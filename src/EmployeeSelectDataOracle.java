import java.sql.*;

public class EmployeeSelectDataOracle {
    public static void main(String[] args) {
        String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String USER = "SYSTEM";
        String PASSWORD = "BCA5C";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // SQL query to select data from Employee and Department
            String sql = "SELECT e.Eid, e.Ename, e.Salary, e.Address, d.Dname " +
                         "FROM Employee e INNER JOIN Department d ON e.Did = d.Did";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Eid | Ename | Salary | Address | Department");
            System.out.println("--------------------------------------------");

            // Iterate through the result set and print data
            while (rs.next()) {
                int eid = rs.getInt("Eid");
                String ename = rs.getString("Ename");
                double salary = rs.getDouble("Salary");
                String address = rs.getString("Address");
                String dname = rs.getString("Dname");

                System.out.println(eid + " | " + ename + " | " + salary + " | " + address + " | " + dname);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
