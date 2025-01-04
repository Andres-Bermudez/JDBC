package com.platzi;

import com.platzi.model.Employee;
import com.platzi.repository.EmployeeRepository;
import com.platzi.view.SwingApp;

public class Main {

    public static void main(String[] args) {

        // Iniciar aplicacion con interfaz grafica.
        SwingApp swingApp = new SwingApp();
        swingApp.setVisible(true);

//        // Test manual de la aplicacion.
//        EmployeeRepository employeeRepository = new EmployeeRepository();
//
//        // Obtener todos los registros.
//        employeeRepository.findAll().forEach(System.out::println);
//
//        // Buscar un registro por su id.
//        System.out.println(employeeRepository.getById(1L));
//
//        // Guardar un nuevo registro.
//        Employee employee = new Employee(
//                null,
//                "Natalia",
//                "Soarin",
//                "Junco",
//                "Natalia@email.com",
//                120000.0
//        );
//        employeeRepository.save(employee);
//
//        // Actualizar un registro.
//        Employee employee2 = new Employee(
//                null,
//                "Laura",
//                "Cabrera",
//                "Benitez",
//                "Laura@email.com",
//                75000.0
//        );
//        employeeRepository.update(5L, employee2);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 40f6ba390f6e581043df916efb4ad328d778cd23
