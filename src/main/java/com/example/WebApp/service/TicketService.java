package com.example.WebApp.service;

import com.example.WebApp.domain.dto.TicketDto;
import com.example.WebApp.domain.entity.Ticket;

import java.util.List;

public interface TicketService {

    List<TicketDto> view(TicketDto ticketDto);

    List<TicketDto> list();

    TicketDto create(TicketDto ticketDto) throws Exception;

    TicketDto update(TicketDto ticketDto) throws Exception;

    TicketDto delete(TicketDto ticketDto) throws Exception;

//    List<TicketDto> assignWatcher(Long employeeId, Long ticket) throws Exception;
}
