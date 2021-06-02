package com.example.WebApp.service;

import com.example.WebApp.domain.dto.EmployeeDto;
import com.example.WebApp.domain.entity.Ticket;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;

public interface EmployeeService {

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<EmployeeDto> view(Long employeeNumber);

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<EmployeeDto> list();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    EmployeeDto create(EmployeeDto employeeDto) throws Exception;

//    @PreAuthorize("hasRole('ADMIN')")
    EmployeeDto update(EmployeeDto employeeDto) throws Exception;

//    @PreAuthorize("hasRole('ADMIN')")
    EmployeeDto delete(Long employeeNumber) throws Exception;

//    @PreAuthorize("hasRole('ADMIN')")
    Ticket assignedTicket(EmployeeDto employeeDto) throws Exception;

}
