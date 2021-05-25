package com.example.WebApp.service.impl;

import com.example.WebApp.domain.dto.EmployeeDto;
import com.example.WebApp.domain.entity.Employee;
import com.example.WebApp.domain.entity.Ticket;
import com.example.WebApp.domain.exceptionHandler.resourceNotFoundHandler;
import com.example.WebApp.repository.EmployeeRepository;
import com.example.WebApp.repository.TicketRepository;
import com.example.WebApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TicketRepository ticketRepository;

    /*
    view: look for CERTAIN employee
     */

    @Override
    public Employee view(Long employeeNumber) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber);
        return employee;
    }
    /*
        list: list ALL employees regardless
         */
    @Override
    public List<EmployeeDto> list() {
        return employeeRepository.findAll().stream().map(employee -> {
            EmployeeDto employeeDto1 = new EmployeeDto();
            employeeDto1.setEmployeeNumber(employee.getEmployeeNumber());
            employeeDto1.setFirstName(employee.getFirstName());
            employeeDto1.setMiddleName(employee.getMiddleName());
            employeeDto1.setLastName(employee.getLastName());
            employeeDto1.setDepartment(employee.getDepartment());
            return employeeDto1;
        }).collect(Collectors.toList());
    }

    @Override
    public Employee create(Employee employee) throws Exception{
        Boolean employeeNumberExists = employeeRepository.existsByEmployeeNumber(employee.getEmployeeNumber());

        if (employeeNumberExists == true){
            throw new Exception("Employee#" + employee.getEmployeeNumber() + " already exists!");
        }
/*

        Employee createEmployee = null;

        createEmployee.setEmployeeNumber(employee.getEmployeeNumber());
        createEmployee.setFirstName(employee.getFirstName());
        createEmployee.setMiddleName(employee.getMiddleName());
        createEmployee.setLastName(employee.getLastName());
        createEmployee.setDepartment(employee.getDepartment());
*/

//        Employee employeeCreated = employeeRepository.save(employee);

        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) throws Exception {
        Boolean employeeNumberExists = employeeRepository.existsByEmployeeNumber(employee.getEmployeeNumber());
        if (employeeNumberExists == null){
            throw new Exception("Employee#" + employeeNumberExists + " doesn't exists!");
        }
        Employee employeeUpdate = employeeRepository.findByEmployeeNumber(employee.getEmployeeNumber());

        if (employee.getFirstName() != null){
            employeeUpdate.setFirstName(employee.getFirstName());
        }
        if (employee.getMiddleName() != null){
            employeeUpdate.setMiddleName(employee.getMiddleName());
        }
        if (employee.getLastName() != null){
            employeeUpdate.setLastName(employee.getLastName());
        }
        if (employee.getDepartment() != null){
            employeeUpdate.setDepartment(employee.getDepartment());
        }

        final Employee employeeUpdated = employeeRepository.save(employeeUpdate);
        return employeeUpdated;
    }

    @Override
    public void delete(Long employeeNumber) throws Exception{
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber);

        if (employee == null){
            throw new Exception("Employee#" + employeeNumber + " doesn't exists");
        }

        employeeRepository.delete(employee);
    }

    @Override
    public Ticket assignedTicket(Long ticketId, Long employeeNumber) throws Exception{
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new Exception("Ticker Id#" + ticketId + " doesn't exists"));
//        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber);
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber) == null ? null : employeeRepository.findByEmployeeNumber(employeeNumber);
        ticket.setAssignee(employee);
        final Ticket assignedTicket = ticketRepository.save(ticket);
        return assignedTicket;
    }
}
