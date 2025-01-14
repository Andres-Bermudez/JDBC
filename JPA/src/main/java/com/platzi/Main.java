package com.platzi;

import java.util.List;
import com.platzi.entity.Employee;
import jakarta.persistence.EntityManager;
import com.platzi.util.UtilEntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager= UtilEntityManagerFactory.getEntityManager();

        List<Employee> employeeList =
                entityManager.createQuery("SELECT * FROM employees", Employee.class)
                             .getResultList();

        employeeList.forEach(System.out::println);
    }
}