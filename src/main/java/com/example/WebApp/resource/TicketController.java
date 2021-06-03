package com.example.WebApp.resource;

import com.example.WebApp.domain.dto.TicketDto;
import com.example.WebApp.domain.entity.Ticket;
import com.example.WebApp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
public class TicketController{

    @Autowired
    TicketService ticketService;

    @GetMapping
    public Principal retrievePrincipal(Principal principal){
        return principal;
    }

    @GetMapping("/")
    private ResponseEntity<Ticket> getAllTicket(){
        return new ResponseEntity(ticketService.list(), HttpStatus.OK);
    }

    @GetMapping("/viewTicket")
    public ResponseEntity<TicketDto> getTicket(@RequestBody TicketDto ticketDto){
        return new ResponseEntity(ticketService.view(ticketDto), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) throws Exception {
        return new ResponseEntity(ticketService.create(ticketDto), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Ticket> updateTicket(@RequestBody TicketDto ticketDto) throws Exception{
        return new ResponseEntity(ticketService.update(ticketDto), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public Map<String, Boolean> deleteTicket(@RequestBody TicketDto ticketDto)
            throws Exception {
        ticketService.delete(ticketDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/watchers")
    public ResponseEntity<Ticket> addWatchers(@RequestBody TicketDto ticketDto) throws Exception {
        return new ResponseEntity(ticketService.assignWatcher(ticketDto), HttpStatus.OK);
    }
}


