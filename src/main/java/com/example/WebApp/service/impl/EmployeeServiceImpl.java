package com.example.WebApp.service.impl;

import com.example.WebApp.domain.dto.EmployeeDto;
import com.example.WebApp.domain.entity.Employee;
import com.example.WebApp.domain.entity.Ticket;
import com.example.WebApp.repository.EmployeeRepository;
import com.example.WebApp.repository.TicketRepository;
import com.example.WebApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<EmployeeDto> view(Long employeeNumber) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setEmployeeNumber(employee.getEmployeeNumber());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setMiddleName(employee.getMiddleName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setDepartment(employee.getDepartment());
        List<EmployeeDto> viewEmployee = new ArrayList<>();
        viewEmployee.add(employeeDto);

        return viewEmployee;
    }

    @Override
    public List<EmployeeDto> list() {
        return employeeRepository.findAll().stream().map(employee -> {
            EmployeeDto employeeDto1 = new EmployeeDto();
            employeeDto1.setId(employee.getId());
            employeeDto1.setEmployeeNumber(employee.getEmployeeNumber());
            employeeDto1.setFirstName(employee.getFirstName());
            employeeDto1.setMiddleName(employee.getMiddleName());
            employeeDto1.setLastName(employee.getLastName());
            employeeDto1.setDepartment(employee.getDepartment());
            return employeeDto1;
        }).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) throws Exception{
        Employee employee = employeeRepository.findByEmployeeNumber(employeeDto.getEmployeeNumber());

        if (employee != null){
            throw new Exception("Employee#" + employee + " already exists!");
        } else {
            employee = new Employee();
            employee.setEmployeeNumber(employeeDto.getEmployeeNumber());
            employee.setFirstName(employeeDto.getFirstName());
            employee.setMiddleName(employeeDto.getMiddleName());
            employee.setLastName(employeeDto.getLastName());
            employee.setDepartment(employeeDto.getDepartment());

            employee = employeeRepository.save(employee);
            employeeDto.setId(employee.getId());
        }

        return employeeDto;
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) throws Exception {
        Employee employeeUpdate = employeeRepository.findByEmployeeNumber(employeeDto.getEmployeeNumber());

        if (employeeUpdate == null){
            throw new Exception("Employee#" + employeeDto.getEmployeeNumber() + " doesn't exists!");
        } else {
            if (employeeDto.getFirstName() != null){
                employeeUpdate.setFirstName(employeeDto.getFirstName());
            }
            if (employeeDto.getMiddleName() != null){
                employeeUpdate.setMiddleName(employeeDto.getMiddleName());
            }
            if (employeeDto.getLastName() != null){
                employeeUpdate.setLastName(employeeDto.getLastName());
            }
            if (employeeDto.getDepartment() != null){
                employeeUpdate.setDepartment(employeeDto.getDepartment());
            }
        }

        final Employee employeeUpdated = employeeRepository.save(employeeUpdate);
        employeeDto.setId(employeeUpdated.getId());
        return employeeDto;
    }

    @Override
    public EmployeeDto delete(Long employeeDelete) throws Exception{
        Employee employee = employeeRepository.findByEmployeeNumber(employeeDelete);

        if (employee == null){
            throw new Exception("Employee#" + employeeDelete + " doesn't exists");
        }

        employeeRepository.delete(employee);
        return null;
    }

    @Override
    public Ticket assignedTicket(EmployeeDto employeeDto) throws Exception{
        Ticket ticket = ticketRepository.findById(employeeDto.getTicket())
                .orElseThrow(() -> new Exception("Ticker Id#" + employeeDto.getTicket() + " doesn't exists"));
        Employee employee = employeeRepository.findByEmployeeNumber(employeeDto.getEmployeeNumber()) == null ?
                null : employeeRepository.findByEmployeeNumber(employeeDto.getEmployeeNumber());
        ticket.setAssignee(employee);
        final Ticket assignedTicket = ticketRepository.save(ticket);
        return assignedTicket;
    }


}
