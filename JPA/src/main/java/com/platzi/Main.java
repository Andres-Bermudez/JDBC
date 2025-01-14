package com.platzi;

import java.util.List;
import com.platzi.entity.Employee;
import jakarta.persistence.EntityManager;
import com.platzi.util.UtilEntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager= UtilEntityManagerFactory.getEntityManager();

        // Obtener lista de empleados.
        List<Employee> employeeList =
                entityManager.createQuery("SELECT * FROM employees", Employee.class)
                             .getResultList();

        employeeList.forEach(System.out::println);

        // Buscar un empleado por su Id.
        Employee employee = entityManager.find(Employee.class, 1);
        System.out.println("Empleado encontrado: " + employee);

        // Crear un nuevo empleado.
        Employee newEmployee = new Employee(
                null,
                "Laura",
                "Meneses",
                "Urrutia",
                "laura.meneses@email.com",
                145000.0
        );

        entityManager.getTransaction(),begin(); // Iniciar la transaccion.
        entityManager.persist(newEmployee);
        entityManager.getTransaction().commit(); // Hacer commit de la transaccion.
        System.out.println("Se ha creado un nuevo empleado.");

        // Actualizar un registro.
        Employee updateEmployee = entityManager.find(Employee.class, 1);
        updateEmployee.setEmail("newEmail@email.com");

        entityManager.getTransaction().begin();
        entityManager.merge(updateEmployee);
        entityManager.getTransaction().commit();

        // Eliminar un registro.
        Employee deleteEmployee = entityManager.find(Employee.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(deleteEmployee);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
