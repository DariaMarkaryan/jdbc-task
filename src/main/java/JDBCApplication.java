import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCApplication {

    public static void main(String[] args) {
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee", "root", "meta")) {

            createTable(connection);
            deleteAllRecords(connection);
            Employee e1 = new Employee("Joshn", "Smith", "Development", 5000);
            Employee e2 = new Employee("Nick", "Johnson", "Development", 6000);
            Employee e3 = new Employee("Mary", "Johnson", "Sales", 4000);
            Employee e4 = new Employee("Christopher", "Robin", "Sales", 4000);
            Employee e5 = new Employee("Harry", "Gates", "Management", 8000);
            createEmployee(connection, e1);
            createEmployee(connection, e2);
            createEmployee(connection, e3);
            createEmployee(connection, e4);
            createEmployee(connection, e5);
            Map<String, Integer> answer = getSalaryByDepartments(connection);
            for(Map.Entry<String,Integer> e : answer.entrySet()) {
                System.out.println(e.getKey() + ": " + e.getValue());
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private static void createTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sqlStatement = "CREATE TABLE IF NOT EXISTS employee" +
                    "(id INT PRIMARY KEY AUTO_INCREMENT," +
                    "first_name VARCHAR(20) NOT NULL," +
                    "second_name VARCHAR(20) NOT NULL," +
                    "department VARCHAR(20) NOT NULL," +
                    "salary INT NOT NULL);";
            statement.execute(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createEmployee(Connection connection, Employee emp) {
        try {
            String sqlStatement =
                    "INSERT INTO employee(first_name, second_name, department, salary) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getSecondName());
            ps.setString(3, emp.getDepartment());
            ps.setInt(4, emp.getSalary());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static Map<String, Integer> getSalaryByDepartments(Connection connection) {
        Map<String, Integer> res = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            String sqlStatement = "SELECT department, SUM(salary) AS sum FROM employee "+
                    "GROUP BY department;";
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            while(resultSet.next()){
                res.put(resultSet.getString("department"), resultSet.getInt("sum"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    private static void deleteAllRecords(Connection connection){
        try (Statement st = connection.createStatement()){
            String sqlStatement = "TRUNCATE TABLE employee;";
            st.execute(sqlStatement);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

//    private static void updateEmployee(Connection connection) {
//    }
//
//    private static Employee getEmployee(Connection connection) {
//        return null;
//    }
//
//    private static List<Employee> getAllEmployers(Connection connection) {
//        return null;
//    }
//
//    private static void deleteEmployee(Connection connection) {
//    }
}
