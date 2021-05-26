package com.example.WebApp.service;

import com.example.WebApp.domain.dto.EmployeeDto;
import com.example.WebApp.domain.entity.Ticket;

import java.util.List;

/*
    Employee DTO Generic Interface
 */

public interface EmployeeService {

    List<EmployeeDto> view(Long employeeNumber);

    List<EmployeeDto> list();

    EmployeeDto create(EmployeeDto employeeDto) throws Exception;

    EmployeeDto update(EmployeeDto employeeDto) throws Exception;

    EmployeeDto delete(Long employeeNumber) throws Exception;

    Ticket assignedTicket(Long ticketId, Long employeeNumber) throws Exception;

}
