package com.example.WebApp.service.impl;

import com.example.WebApp.domain.dto.TicketDto;
import com.example.WebApp.domain.entity.Employee;
import com.example.WebApp.domain.entity.Ticket;
import com.example.WebApp.repository.EmployeeRepository;
import com.example.WebApp.repository.TicketRepository;
import com.example.WebApp.service.EmployeeService;
import com.example.WebApp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<TicketDto> view(TicketDto ticketDto) {
        Ticket ticket = ticketRepository.findByTicket(ticketDto.getTicket());
        TicketDto ticketView = new TicketDto();
        ticketView.setTicket(ticket.getTicket());
        ticketView.setTitle(ticket.getTitle());
        ticketView.setDescription(ticket.getDescription());
        ticketView.setStatus(ticket.getStatus());
        ticketView.setSeverity(ticket.getSeverity());
        ticketView.setAssignee(ticket.getAssignee());
        List<TicketDto> viewTicket = new ArrayList<>();
        viewTicket.add(ticketView);
        return viewTicket;
    }

    @Override
    public List<TicketDto> list() {
        return ticketRepository.findAll().stream().map(ticket -> {
            TicketDto ticketDto = new TicketDto();
            ticketDto.setTicket(ticket.getTicket());
            ticketDto.setTitle(ticket.getTitle());
            ticketDto.setDescription(ticket.getDescription());
            ticketDto.setSeverity(ticket.getSeverity());
            ticketDto.setStatus(ticket.getStatus());
            return ticketDto;
        }).collect(Collectors.toList());
    }

    @Override
    public TicketDto create(TicketDto ticketDto) throws Exception {
        Ticket ticket = ticketRepository.findByTicket(ticketDto.getTicket());
        if (ticket !=  null){
            throw new Exception("Ticket Id: " + ticketDto.getTicket() + " already exists!");
        } else {
            ticket = new Ticket();
            ticket.setTitle(ticketDto.getTitle());
            ticket.setDescription(ticketDto.getDescription());
            ticket.setStatus(ticketDto.getStatus());
            ticket.setSeverity(ticketDto.getSeverity());

            ticket = ticketRepository.save(ticket);
            ticketDto.setTicket(ticket.getTicket());
        }

        return ticketDto;
    }

    @Override
    public TicketDto update(TicketDto ticketDto) throws Exception {
        Ticket ticketUpdate = ticketRepository.findByTicket(ticketDto.getTicket());

        if (ticketUpdate == null){
            throw new Exception("Employee#" + ticketDto.getTicket() + " doesn't exists!");
        } else {
            if (ticketDto.getDescription() != null){
                ticketUpdate.setDescription(ticketDto.getDescription());
            }
            if (ticketDto.getTitle() != null){
                ticketUpdate.setTitle(ticketDto.getTitle());
            }
            if (ticketDto.getStatus() != null){
                ticketUpdate.setStatus(ticketDto.getStatus());
            }
            if (ticketDto.getSeverity() != null){
                ticketUpdate.setSeverity(ticketDto.getSeverity());
            }
        }

        final Ticket ticketUpdated = ticketRepository.save(ticketUpdate);
        ticketDto.setTicket(ticketUpdated.getTicket());
        return ticketDto;
    }

    @Override
    public TicketDto delete(TicketDto ticketDto) throws Exception {
        Ticket ticket = ticketRepository.findByTicket(ticketDto.getTicket());
        if (ticket == null){
            throw new Exception("Ticket Id: " + ticketDto.getTicket() + " doesn't exist");
        }

        ticketRepository.delete(ticket);
        return null;
    }

/*    @Override
    public List<TicketDto> assignWatcher(Long employeeId, Long ticket) throws Exception {
        Ticket ticketAssign = ticketRepository.findByTicket(ticket);
        Employee watchee = employeeRepository.findByEmployeeNumber(employeeId);
        if (ticketAssign == null){
            throw new Exception("Ticket Id: " + ticket + " doesn't exist");
        } else if (watchee == null){
            throw new Exception("Employee Number: " + employeeId + " doesn't exist");
        }
        HashSet<Employee> addWatchers = new HashSet<Employee>();
        addWatchers.add(watchee);
        ticketAssign.setWatchers(addWatchers);
        return null;
    }*/
}
