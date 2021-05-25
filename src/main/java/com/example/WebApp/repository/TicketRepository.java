package com.example.WebApp.repository;

import com.example.WebApp.domain.entity.Employee;
import com.example.WebApp.domain.entity.Ticket;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Boolean existsByAssignee(Employee assignee);
}
