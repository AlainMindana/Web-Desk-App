package com.example.WebApp.service;

import com.example.WebApp.domain.dto.TicketDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface TicketService {

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<TicketDto> view(TicketDto ticketDto);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<TicketDto> list();

    @PreAuthorize("hasRole('ADMIN')")
    TicketDto create(TicketDto ticketDto) throws Exception;

    @PreAuthorize("hasRole('ADMIN')")
    TicketDto update(TicketDto ticketDto) throws Exception;

    @PreAuthorize("hasRole('ADMIN')")
    TicketDto delete(TicketDto ticketDto) throws Exception;

    @PreAuthorize("hasRole('ADMIN')")
    List<TicketDto> assignWatcher(TicketDto ticketDto) throws Exception;
}
