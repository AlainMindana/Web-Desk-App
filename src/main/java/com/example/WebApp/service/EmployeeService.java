package com.example.WebApp.service;

import com.example.WebApp.domain.dto.EmployeeDto;
import com.example.WebApp.domain.entity.Employee;
import com.example.WebApp.domain.entity.Ticket;

import java.util.List;

public interface EmployeeService {

    Employee view(Long employeeNumber);

    List<EmployeeDto> list();

    Employee create(Employee employee) throws Exception;

    Employee update(Employee employee) throws Exception;

    void delete(Long employeeNumber) throws Exception;

    Ticket assignedTicket(Long ticketId, Long employeeNumber) throws Exception;

}
