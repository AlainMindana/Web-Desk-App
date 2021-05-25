package com.example.WebApp.domain.dto;

//import com.example.WebApp.domain.entity.Ticket;
import com.example.WebApp.domain.reference.Department;

import java.util.List;

//Model

public class EmployeeDto {
    private Long id;
    private Long employeeNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private Department department;
//    private List<Ticket> employeeTickets;
//
//    public List<Ticket> getEmployeeTickets() {
//        return employeeTickets;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
