package com.example.WebApp.repository;

import com.example.WebApp.domain.entity.Employee;
import com.example.WebApp.domain.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Boolean existsByAssignee(Employee assignee);
    Ticket findByTicket(Long id);
}
