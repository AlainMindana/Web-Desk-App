package com.example.WebApp.resource;

import com.example.WebApp.domain.entity.Employee;
import com.example.WebApp.domain.entity.Ticket;
import com.example.WebApp.domain.exceptionHandler.resourceNotFoundHandler;
import com.example.WebApp.repository.EmployeeRepository;
import com.example.WebApp.repository.TicketRepository;
import com.example.WebApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(/*"/api/v1/"*/  "/employee/")
public class EmployeeController {

/*    private EmployeeRepository employeeRepository;
    private TicketRepository ticketRepository;*/
    @Autowired
    EmployeeService employeeService;


/*
    @GetMapping("/employee")
    private List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }
*/

    @GetMapping()
    private ResponseEntity<Employee> getAllEmployee(){
        return new ResponseEntity(employeeService.list(), HttpStatus.OK);
    }


/*    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "employeeId") Long employeeId)
            throws resourceNotFoundHandler {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new resourceNotFoundHandler("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }*/
a
    @GetMapping("/{employeeNumber}"){
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "employeeNumber") Long employeeNumber) {
        return new ResponseEntity(employeeService.view(employeeNumber), HttpStatus.OK);
    }

/*
    @GetMapping("/employee/empnum/{employeeNumber}")
    public ResponseEntity<Employee> getEmployeeByEmployeeNumber(@PathVariable(name = "employeeNumber") Long employeeNumber){
        return new ResponseEntity<Employee>(employeeRepository.findByEmployeeNumber(employeeNumber), HttpStatus.OK);
    }*/

    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws Exception {
        return ResponseEntity.ok(employeeService.create(employee));
    }


 /*   @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        Boolean checkEmpNumExists = employeeRepository.existsByEmployeeNumber(employee.getEmployeeNumber());

        if (checkEmpNumExists){
            System.out.println("Employee Number already exists!");
            System.exit(0);
        }

        return employeeRepository.save(employee);
    }
*/

    @PutMapping("/{employeeNumber}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "employeeNumber") Long employeeNumber,
                                                   @RequestBody Employee employeeInfo) throws Exception {
        return new ResponseEntity(employeeService.update(employeeInfo), HttpStatus.OK);
    }


  /*
    @PutMapping("/employee/{employeeNumber}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "employeeNumber") Long employeeNumber,
                                                   @RequestBody Employee employeeInfo){
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber);

        if (employeeInfo.getFirstName() != null){
            employee.setFirstName(employeeInfo.getFirstName());
        }

        if (employeeInfo.getLastName() != null){
            employee.setLastName(employeeInfo.getLastName());
        }

        if (employeeInfo.getDepartment() != null){
            employee.setDepartment(employeeInfo.getDepartment());

        }

        if (employeeInfo.getMiddleName() != null){
            employee.setMiddleName(employeeInfo.getMiddleName());
        }

        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
*/
    /*
    @DeleteMapping("/employee/{employeeNumber}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(name = "employeeNumber") Long employeeNumber) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber);
        Boolean checkExists = ticketRepository.existsByAssignee(employee);

        if (checkExists){
            System.out.println("Cannot delete Employee Number: " + employeeNumber);
            System.exit(0);
        }

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("--Employee Deleted--", Boolean.TRUE);
        return response;
    }*/

    @DeleteMapping("/employee/{employeeNumber}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(name = "employeeNumber") Long employeeNumber) throws Exception {
        employeeService.delete(employeeNumber);
        Map<String, Boolean> response = new HashMap<>();
        response.put("--Employee Deleted--", Boolean.TRUE);
        return response;
    }

    @PutMapping("/assignTicket/{ticketId}/{employeeNumber}")
    public ResponseEntity<Object> assignTicket(@PathVariable(name = "ticketId") Long ticketId, @PathVariable(name = "employeeNumber") Long
                                               employeeNumber) throws Exception {
        return new ResponseEntity(employeeService.assignedTicket(ticketId, employeeNumber), HttpStatus.OK);
    }
    }



