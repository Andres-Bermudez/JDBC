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
            // Conexion con la base de datos
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
        String sql = """
                     SELECT *
                     FROM employees
                     WHERE id = ?
                     """;

        try {
            connection = DriverManager.getConnection(url, user, password);

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
}
