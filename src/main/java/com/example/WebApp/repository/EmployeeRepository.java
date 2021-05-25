package com.example.WebApp.repository;

import com.example.WebApp.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    List<Employee> findByEmployeeNumber(Long employeeNumber);
    Employee findByEmployeeNumber(Long employeeNumber);
    Boolean existsByEmployeeNumber(Long employeeNumber);
}
