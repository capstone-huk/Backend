package com.example.demo.ticket.dto;

import com.example.demo.ticket.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketListResponseDto {
    private int ticketCount;
    private List<TicketResponseDto> tickets;

    public static TicketListResponseDto from(List<Ticket> tickets) {
        return TicketListResponseDto.builder()
                .ticketCount(tickets.size())
                .tickets(
                        tickets.stream().map(ticket -> TicketResponseDto.from(ticket)).toList()
                )
                .build();
    }
}
