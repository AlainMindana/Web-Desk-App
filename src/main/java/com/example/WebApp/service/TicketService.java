package com.example.WebApp.service;

import com.example.WebApp.domain.dto.TicketDto;

import java.util.List;

public interface TicketService {

    List<TicketDto> view(TicketDto ticketDto);

    List<TicketDto> list();

    TicketDto create(TicketDto ticketDto) throws Exception;

    TicketDto update(TicketDto ticketDto) throws Exception;

    TicketDto delete(TicketDto ticketDto) throws Exception;

    List<TicketDto> assignWatcher(TicketDto ticketDto) throws Exception;
}
