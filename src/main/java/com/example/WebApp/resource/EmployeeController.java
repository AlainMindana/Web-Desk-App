package com.example.WebApp.resource;

import com.example.WebApp.domain.dto.EmployeeDto;
import com.example.WebApp.domain.entity.Employee;
import com.example.WebApp.repository.EmployeeRepository;
import com.example.WebApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    private ResponseEntity<Employee> getAllEmployee(){
        return new ResponseEntity(employeeService.list(), HttpStatus.OK);
    }

    @GetMapping("/employeeFind")
    public ResponseEntity<Employee> getEmployeeById(@RequestBody Employee employee) {
        Long employeeNum = employee.getEmployeeNumber();
        return new ResponseEntity(employeeService.view(employeeNum), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeCreate) throws Exception {
        return new ResponseEntity(employeeService.create(employeeCreate), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeDto employeeUpdate) throws Exception {
        return new ResponseEntity(employeeService.update(employeeUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public Map<String, Boolean> deleteEmployee(@RequestBody Employee employee) throws Exception {
        Long employeeNumber = employee.getEmployeeNumber();
        employeeService.delete(employeeNumber);
        Map<String, Boolean> response = new HashMap<>();
        response.put("--Employee Deleted--", Boolean.TRUE);
        return response;
    }

    @PutMapping("/assignTicket")
    public ResponseEntity<Object> assignTicket(EmployeeDto employeeDto) throws Exception {
        return new ResponseEntity(employeeService.assignedTicket(employeeDto), HttpStatus.OK);
    }
    }



