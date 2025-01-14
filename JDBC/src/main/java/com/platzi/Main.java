package com.platzi;

import java.sql.Connection;
import java.sql.SQLException;
import com.platzi.view.SwingApp;
import com.platzi.model.Employee;
import com.platzi.util.DatabaseConnection;
import com.platzi.repository.EmployeeRepository;

public class Main {

    public static void main(String[] args) throws SQLException {

        // Iniciar aplicacion con interfaz grafica.
        SwingApp swingApp = new SwingApp();
        swingApp.setVisible(true);


//        // Ejemplo de transacciones en bases de datos.
//        // 1. Crear las intancias que se utilizaran.
//        Employee employee1 = new Employee(
//                null,
//                "Natalia",
//                "Rodriguez",
//                "Perez",
//                "natalia.rodriguez@email.com",
//                65000.0
//        );
//        Employee employee2 = new Employee(
//                null,
//                "Fernando",
//                "Benitez",
//                "Romero",
//                "fernando.romero@email.com",
//                72000.0
//        );
//        Employee employee3 = new Employee(
//                null,
//                "Camila",
//                "Arango",
//                "Benavidez",
//                "camila.arango@email.com",
//                77000.0
//        );
//
//        try (Connection connection = DatabaseConnection.getInstance()) {
//            // Deshabilitar el autocommit para manejar manualmente la transacción
//            connection.setAutoCommit(false);
//
//            // Crear el repositorio y realizar las operaciones
//            EmployeeRepository employeeRepository = new EmployeeRepository();
//            employeeRepository.update(3L, employee1);
//            employeeRepository.save(employee2);
//            employeeRepository.save(employee3);
//
//            // Confirmar la transacción si todo salió bien
//            connection.commit();
//            System.out.println("Transacción completada exitosamente.");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error: " + e.getMessage());
//            try {
//                if (connection != null) {
//                    connection.rollback(); // Revertir cambios si ocurre un error
//                    System.out.println("Transacción revertida.");
//                }
//            } catch (SQLException rollbackEx) {
//                rollbackEx.printStackTrace();
//            }
//        }
    }
}
