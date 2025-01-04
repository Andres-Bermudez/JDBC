package com.platzi.model;

public class Employee {

    // Attributes.
    private Long id;
    private String first_name;
    private String pa_surname;
    private String ma_surname;
    private String email;
    private Double salary;

    // Empty Constructor.
    public Employee() {
    }

    // Constructor.
    public Employee(
            Long id,
            String first_name,
            String pa_surname,
            String ma_surname,
            String email,
            Double salary
    ) {
        this.id = id;
        this.first_name = first_name;
        this.pa_surname = pa_surname;
        this.ma_surname = ma_surname;
        this.email = email;
        this.salary = salary;
    }

    // ToString Method.
    @Override
    public String toString() {
        return "\nEMPLOYEE:" +
                "\nId: " + id +
                "\nFirst Name: " + first_name +
                "\nPa Surname: " + pa_surname +
                "\nMa Surname: " + ma_surname +
                "\nEmail: " + email +
                "\nSalary: " + salary;
    }

    // Getters and Setters.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPa_surname() {
        return pa_surname;
    }

    public void setPa_surname(String pa_surname) {
        this.pa_surname = pa_surname;
    }

    public String getMa_surname() {
        return ma_surname;
    }

    public void setMa_surname(String ma_surname) {
        this.ma_surname = ma_surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
