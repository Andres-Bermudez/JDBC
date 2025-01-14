package com.platzi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "pa_surname")
    private String paSurname;

    @Column(name = "ma_surname")
    private String maSurname;

    private String email;
    private Double salary;

    public Employee() {
    }

    public Employee(Long id,
                    String firstName,
                    String paSurname,
                    String maSurname,
                    String email,
                    Double salary
    ) {
        this.id = id;
        this.firstName = firstName;
        this.paSurname = paSurname;
        this.maSurname = maSurname;
        this.email = email;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPaSurname() {
        return paSurname;
    }

    public void setPaSurname(String paSurname) {
        this.paSurname = paSurname;
    }

    public String getMaSurname() {
        return maSurname;
    }

    public void setMaSurname(String maSurname) {
        this.maSurname = maSurname;
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

    @Override
    public String toString() {
        return "\nEmployee" +
                "\nId:" + id +
                "\nFirst Name: " + firstName +
                "\nPa Surname: " + paSurname +
                "\nMa Surname: " + maSurname +
                "\nEmail: " + email +
                "\nSalary: " + salary;
    }
}
