package crud;

import java.sql.*;

public class DataService {

    // Credenciales de la base de datos.
    private String url = System.getenv("URL_DATABASE");
    private String user = System.getenv("USER_DATABASE");
    private String password = System.getenv("PASSWORD_DATABASE");

    // Objetos JDBC para interactuar con la base de datos.
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void getAllEmployees() {
        try {
            // Conexion con la base de datos.
            connection = DriverManager.getConnection(url, user, password);

            // Crear la consulta.
            statement = connection.createStatement();
            resultSet = statement.executeQuery("""
                                                   SELECT *
                                                   FROM employees;
                                                   """);

            // Imprimir los registros consultados de la base de datos.
            while (resultSet.next()) {
                System.out.println(resultSet.getString("first_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getEmployeeById(Long idEmployee) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            String sql = """
                         SELECT *
                         FROM employees
                         WHERE id = ?
                         """;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, idEmployee);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("first_name"));
            } else {
                System.out.println("\nNo se encontro ningun registro con este id.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateEmployeeSalary(Long idEmployee, Double newSalary) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            String sql = """
                         UPDATE employees
                         SET salary = ?
                         WHERE id = ?;
                         """;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, newSalary);
            preparedStatement.setLong(2, idEmployee);

            preparedStatement.executeUpdate();
            System.out.println("\nEl salario del empleado con el id:" +
                    " (" + idEmployee + ") fue actualizado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteEmployee(Long idEmployee) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            String sql = """
                         DELETE FROM employees
                         WHERE id = ?;
                         """;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, idEmployee);

            preparedStatement.executeUpdate();
            System.out.println(" Se elimino el empleado con el id: (" + idEmployee + ") de forma exitosa." );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
