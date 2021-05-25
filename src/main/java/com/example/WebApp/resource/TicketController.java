package com.example.WebApp.resource;

import com.example.WebApp.domain.entity.Employee;
import com.example.WebApp.domain.entity.Ticket;
import com.example.WebApp.domain.exceptionHandler.resourceNotFoundHandler;
import com.example.WebApp.repository.EmployeeRepository;
import com.example.WebApp.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class TicketController{
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/ticket")
    private List<Ticket> getAllTicket(){
        return ticketRepository.findAll();
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable(name = "ticketId") Long ticket)
            throws resourceNotFoundHandler {
        Ticket ticket1 = ticketRepository.findById(ticket)
                .orElseThrow(() -> new resourceNotFoundHandler("Ticket not found for this id :: " + ticket));
        return ResponseEntity.ok().body(ticket1);
    }

    @PostMapping("/ticket")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @PutMapping("/ticket/{ticketId}")
    public ResponseEntity<Ticket> updateEmployee(@PathVariable(value = "ticket") Long ticketId,
                                                   @RequestBody Ticket ticketInfo) throws resourceNotFoundHandler {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new resourceNotFoundHandler("Ticket not found for this id :: " + ticketId));

        ticket.setTicket(ticketInfo.getTicket());
        ticket.setTitle(ticketInfo.getTitle());
        ticket.setDescription(ticketInfo.getDescription());
        ticket.setSeverity(ticketInfo.getSeverity());
        ticket.setStatus(ticketInfo.getStatus());
//        ticket.setAssignee(ticketInfo.getAssignee());
//        ticket.setWatchers(ticketInfo.getWatchers());
        final Ticket updatedTicket = ticketRepository.save(ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/ticket/{ticketId}")
    public Map<String, Boolean> deleteTicket(@PathVariable(name = "ticketId") Long ticketId)
            throws resourceNotFoundHandler {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new resourceNotFoundHandler("Ticket not found for this id :: " + ticketId));
        ticketRepository.delete(ticket);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    /*@PutMapping("/ticket/{ticketId}/{employeeId}")
    public ResponseEntity<Ticket> assignTicket(@PathVariable(name = "ticketId") Long ticketId, @PathVariable(name = "employeeId") Long employeeId)
                                            throws resourceNotFoundHandler {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new resourceNotFoundHandler("Ticket not found for this id :: " + ticketId));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new resourceNotFoundHandler("Employee not found for this is ::  " + employeeId));

        ticket.setAssignee(employee);
        final Ticket assignedTicket = ticketRepository.save(ticket);
        return ResponseEntity.ok(assignedTicket);
    }*/

    @PutMapping("/ticket/{ticketId}/{employeeNumber}")
    public ResponseEntity<Ticket> assignTicket(@PathVariable(name = "ticketId") Long ticketId, @PathVariable(name = "employeeNumber") Long employeeNumber)
            throws resourceNotFoundHandler {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new resourceNotFoundHandler("Ticket not found for this id :: " + ticketId));
        Employee employee1 = employeeRepository.findByEmployeeNumber(employeeNumber);
        ticket.setAssignee(employee1);
        final Ticket assignedTicket = ticketRepository.save(ticket);
        return ResponseEntity.ok(assignedTicket);

    }

/*
    @PutMapping("/ticket/watchers")
public ResponseEntity<Ticket> addWatchers(@RequestBody Ticket watchers*/
/*, @RequestBody List<Employee> employeeWatchers*//*
)
            throws resourceNotFoundHandler{
        Long ticketId = watchers.getTicket();
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new resourceNotFoundHandler("Ticket " +
                ticketId + " doesn't exist"));


        ticket.setWatchers(watchers.getAssignee());

    }
*/


}


