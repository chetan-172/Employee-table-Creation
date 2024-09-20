import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeSelectDataMySQL {
    public static void main(String[] args) {
        String DB_URL = "jdbc:mysql://localhost:3306/cdiv";
        String USER = "root";
        String PASSWORD = "BCA5C";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            Statement stmt = con.createStatement();

            String selectQuery = "SELECT E.Eid, E.Ename, E.Salary, E.Address, D.Dname "
                    + "FROM Employee E "
                    + "INNER JOIN Department D ON E.Did = D.Did";

            ResultSet rs = stmt.executeQuery(selectQuery);

            while (rs.next()) {
                System.out.println("Employee ID: " + rs.getInt("Eid"));
                System.out.println("Employee Name: " + rs.getString("Ename"));
                System.out.println("Salary: " + rs.getDouble("Salary"));
                System.out.println("Address: " + rs.getString("Address"));
                System.out.println("Department: " + rs.getString("Dname"));
                System.out.println();
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
