package com.example.WebApp.domain.entity;

import com.example.WebApp.domain.reference.Department;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
//@DynamicUpdate
@Table(name = "employee",
        uniqueConstraints = @UniqueConstraint(columnNames = {"employee_number"}))
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @OneToOne(mappedBy = "assignee")
    private Long id;

    @Column(name = "employee_number", nullable = false, unique = true, updatable = false)
    private Long employeeNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "department", nullable = false)
    private Department department;


    public Long getId() { return id; }

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
