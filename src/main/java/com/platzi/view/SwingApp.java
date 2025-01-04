package com.platzi.view;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.sql.SQLException;
import com.platzi.model.Employee;
import com.platzi.repository.Repository;
import javax.swing.table.DefaultTableModel;
import com.platzi.repository.EmployeeRepository;

public class SwingApp extends JFrame {

    private final Repository<Employee> employeeRepository;
    private final JTable employeeTable;

    public SwingApp() {
        // Configurar la ventana.
        setTitle("Gestión de Empleados");
        setSize(1000, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear una tabla para mostrar los empleados.
        employeeTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Crear botones para acciones.
        JButton verTodosButton = new JButton("Ver Todos");
        JButton agregarButton = new JButton("Agregar");
        JButton buscarButton = new JButton("Buscar");
        JButton actualizarButton = new JButton("Actualizar");
        JButton eliminarButton = new JButton("Eliminar");

        // Configurar el panel de botones.
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);
        buttonPanel.add(buscarButton);
        buttonPanel.add(actualizarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(verTodosButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Establecer estilos para los botones.
        verTodosButton.setBackground(new Color(8, 82, 40));
        verTodosButton.setForeground(Color.WHITE);
        verTodosButton.setFocusPainted(false);

        agregarButton.setBackground(new Color(46, 204, 113));
        agregarButton.setForeground(Color.WHITE);
        agregarButton.setFocusPainted(false);

        buscarButton.setBackground(new Color(198, 116, 64));
        buscarButton.setForeground(Color.WHITE);
        buscarButton.setFocusPainted(false);

        actualizarButton.setBackground(new Color(52, 152, 219));
        actualizarButton.setForeground(Color.WHITE);
        actualizarButton.setFocusPainted(false);

        eliminarButton.setBackground(new Color(231, 76, 60));
        eliminarButton.setForeground(Color.WHITE);
        eliminarButton.setFocusPainted(false);

        // Crear el objeto Repository para acceder a la base de datos.
        employeeRepository = new EmployeeRepository();

        // Cargar los empleados iniciales en la tabla
        refreshEmployeeTable();

        // Agregar ActionListener para los botones.
        verTodosButton.addActionListener(e -> refreshEmployeeTable());

        agregarButton.addActionListener(e -> {
            try {
                addEmployee();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        buscarButton.addActionListener(e -> findEmployeeById());
        actualizarButton.addActionListener(e -> updateEmployee());
        eliminarButton.addActionListener(e -> deleteEmployee());
    }

    private void refreshEmployeeTable() {
        // Obtener la lista actualizada de empleados desde la base de datos.
        try {
            List<Employee> employees = employeeRepository.findAll();

            // Crear un modelo de tabla y establecer los datos de los empleados.
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Id");
            model.addColumn("Nombre");
            model.addColumn("Apellido Paterno");
            model.addColumn("Apellido Materno");
            model.addColumn("Email");
            model.addColumn("Salario");

            for (Employee employee : employees) {
                Object[] rowData = {
                        employee.getId(),
                        employee.getFirst_name(),
                        employee.getPa_surname(),
                        employee.getMa_surname(),
                        employee.getEmail(),
                        employee.getSalary()
                };
                model.addRow(rowData);
            }

            // Establecer el modelo de tabla actualizado.
            employeeTable.setModel(model);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los empleados de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addEmployee() throws SQLException {
        // Crear un formulario para agregar un empleado.
        JTextField nombreField = new JTextField();
        JTextField paternoField = new JTextField();
        JTextField maternoField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField salarioField = new JTextField();

        Object[] fields = {
                "Nombre:", nombreField,
                "Apellido Paterno:", paternoField,
                "Apellido Materno:", maternoField,
                "Email:", emailField,
                "Salario:", salarioField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Agregar Empleado", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Validaciones de campos vacios.
            if (nombreField.getText().trim().isEmpty() ||
                paternoField.getText().trim().isEmpty() ||
                maternoField.getText().trim().isEmpty() ||
                emailField.getText().trim().isEmpty() ||
                salarioField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear un nuevo objeto Employee con los datos ingresados.
            Employee employee = new Employee();
            employee.setFirst_name(nombreField.getText());
            employee.setPa_surname(paternoField.getText());
            employee.setMa_surname(maternoField.getText());

            // Verificar si el email ingresado es valido.
            if (emailField.getText().matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$")) {
                employee.setEmail(emailField.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Correo electrónico inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar que el campo salario sea un valor numerico valido.
            try {
                employee.setSalary(Double.parseDouble(salarioField.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El salario debe ser un valor númerico válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Guardar el nuevo empleado en la base de datos.
            employeeRepository.save(employee);

            // Actualizar la tabla con los empleados actualizados.
            refreshEmployeeTable();
            JOptionPane.showMessageDialog(this, "Empleado agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void findEmployeeById() {
        // Obtener el ID del empleado a actualizar.
        String employeeIdStr = JOptionPane.showInputDialog(this, "Ingrese el ID del empleado a buscar:", "Buscar Empleado", JOptionPane.QUESTION_MESSAGE);

        if (employeeIdStr != null) {
            try {
                long employeeId = Long.parseLong(employeeIdStr);

                if (employeeId > 0) {
                    // Buscar el empleado en la base de datos.
                    Employee employee = employeeRepository.getById(employeeId);
                    if (employee == null) {
                        JOptionPane.showMessageDialog(this, "El empleado buscado no existe en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    // Crear un modelo de tabla y establecer los datos del empleado.
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Id");
                    model.addColumn("Nombre");
                    model.addColumn("Apellido Paterno");
                    model.addColumn("Apellido Materno");
                    model.addColumn("Email");
                    model.addColumn("Salario");

                    Object[] rowData = {
                            employee.getId(),
                            employee.getFirst_name(),
                            employee.getPa_surname(),
                            employee.getMa_surname(),
                            employee.getEmail(),
                            employee.getSalary()
                    };
                    model.addRow(rowData);

                    // Establecer el modelo de tabla actualizado.
                    employeeTable.setModel(model);
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese un valor numérico mayor a cero(0)", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el Id del empleado", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void updateEmployee() {
        // Obtener el ID del empleado a actualizar.
        String employeeIdStr = JOptionPane.showInputDialog(this, "Ingrese el ID del empleado a actualizar:", "Actualizar Empleado", JOptionPane.QUESTION_MESSAGE);
        if (employeeIdStr != null) {
            try {
                Long employeeId = Long.parseLong(employeeIdStr);

                // Obtener el empleado desde la base de datos.
                Employee employee = employeeRepository.getById(employeeId);

                if (employee != null) {
                    // Crear un formulario con los datos del empleado.
                    JTextField nombreField = new JTextField(employee.getFirst_name());
                    JTextField apellidoPaternoField = new JTextField(employee.getPa_surname());
                    JTextField apellidoMaternoField = new JTextField(employee.getMa_surname());
                    JTextField emailField = new JTextField(employee.getEmail());
                    JTextField salarioField = new JTextField(String.valueOf(employee.getSalary()));

                    Object[] fields = {
                            "Nombre:", nombreField,
                            "Apellido Paterno:", apellidoPaternoField,
                            "Apellido Materno:", apellidoMaternoField,
                            "Email:", emailField,
                            "Salario:", salarioField
                    };

                    int confirmResult = JOptionPane.showConfirmDialog(this, fields, "Actualizar Empleado", JOptionPane.OK_CANCEL_OPTION);
                    if (confirmResult == JOptionPane.OK_OPTION) {
                        // Actualizar los datos del empleado con los valores ingresados en el formulario.
                        employee.setFirst_name(nombreField.getText());
                        employee.setPa_surname(apellidoPaternoField.getText());
                        employee.setMa_surname(apellidoMaternoField.getText());
                        employee.setEmail(emailField.getText());
                        employee.setSalary(Double.parseDouble(salarioField.getText()));

                        // Guardar los cambios en la base de datos.
                        employeeRepository.update(employeeId, employee);

                        // Actualizar la tabla de empleados en la interfaz.
                        refreshEmployeeTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún empleado con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el Id", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al obtener los datos del empleado de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteEmployee() {
        // Obtener el ID del empleado a eliminar.
        String employeeIdStr = JOptionPane.showInputDialog(this, "Ingrese el Id del empleado a eliminar:", "Eliminar Empleado", JOptionPane.QUESTION_MESSAGE);
        if (employeeIdStr != null) {
            try {
                long employeeId = Long.parseLong(employeeIdStr);
                if (employeeId <= 0) {
                    JOptionPane.showMessageDialog(this, "Ingrese un valor numérico mayor a cero(0)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Confirmar la eliminación del empleado.
                int confirmResult = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el empleado?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmResult == JOptionPane.YES_OPTION) {
                    // Eliminar el empleado de la base de datos.
                    employeeRepository.deleteById(employeeId);

                    // Actualizar la tabla de empleados en la interfaz.
                    refreshEmployeeTable();
                    JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el ID del empleado", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
