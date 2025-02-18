package com.example.demo.ticket.service;

import com.example.demo.ticket.dto.TicketListResponseDto;
import com.example.demo.ticket.entity.Ticket;
import com.example.demo.ticket.repository.TicketRepository;
import com.example.demo.user.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketListResponseDto findTicketList(CustomUserDetails userDetails) {
        List<Ticket> tickets = ticketRepository.findByUser(userDetails.getUser());

        return TicketListResponseDto.from(tickets);
    }
}
