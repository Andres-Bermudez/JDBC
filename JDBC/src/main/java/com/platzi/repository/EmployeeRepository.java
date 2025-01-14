package com.platzi.repository;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.platzi.model.Employee;
import com.platzi.util.DatabaseConnection;

public class EmployeeRepository implements Repository<Employee> {

    // Conexion a la base de datos.
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConecction();
    }

    @Override
    public List<Employee> findAll() {
        try (
                Statement statement = getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM employees;");
        ) {
            if (resultSet != null) {
                List<Employee> employees = new ArrayList<>();

                while (resultSet.next()) {
                    employees.add(createEmployee(resultSet));
                }
                return employees;
            }
        } catch (SQLException e) {
            System.out.println("\nError: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Employee getById(Long idEmployee) {
        String sql = """
                     SELECT *
                     FROM employees
                     WHERE id = ?
                     """;
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement(sql)
        ) {
            preparedStatement.setLong(1, idEmployee);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createEmployee(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("\nError: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Long idEmployee, Employee updatedEmployee) {
        String sql = """
                     UPDATE employees
                     SET first_name = ?,
                         pa_surname = ?,
                         ma_surname = ?,
                         email = ?,
                         salary = ?
                     WHERE id = ?;
                     """;
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement(sql)
        ) {
            preparedStatement.setString(1, updatedEmployee.getFirst_name());
            preparedStatement.setString(2, updatedEmployee.getPa_surname());
            preparedStatement.setString(3, updatedEmployee.getMa_surname());
            preparedStatement.setString(4, updatedEmployee.getEmail());
            preparedStatement.setDouble(5, updatedEmployee.getSalary());
            preparedStatement.setLong(6, idEmployee);

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("\nActualizado con exito. \nTotal de " + rowsAffected + " fila(s) afectada(s).");
        } catch (SQLException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    @Override
    public void save(Employee employee) {
        String sql = """
                     INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary)
                     VALUES(?, ?, ?, ?, ?);
                     """;
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement(sql)
        ) {
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getPa_surname());
            preparedStatement.setString(3, employee.getMa_surname());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setDouble(5, employee.getSalary());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("\nGuardado con exito. \nTotal de " + rowsAffected + " fila(s) afectada(s).");
        } catch (SQLException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long idEmployee) {
        String sql = """
                     DELETE FROM employees
                     WHERE id = ?;
                     """;
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement(sql)
        ) {
            preparedStatement.setLong(1, idEmployee);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("\nEliminacion exitosa. \nTotal de " + rowsAffected + " fila(s) afectada(s).");
        } catch (SQLException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("pa_surname"),
                resultSet.getString("ma_surname"),
                resultSet.getString("email"),
                resultSet.getDouble("salary")
        );
    }
}
