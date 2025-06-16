package com.example.demo.ticket.dto;

import com.example.demo.ticket.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {
    private Long ticketId;
    private Long reviewId;
    private String exhibitionId;
    private String exhibitionTitle;
    private LocalDate date;
    private String ticketImageUrl;

    public static TicketResponseDto from(Ticket ticket) {
        return TicketResponseDto.builder()
                .ticketId(ticket.getId())
                .reviewId(ticket.getReview().getId())
                .exhibitionId(ticket.getReview().getExhibitionSeq())
                .exhibitionTitle(ticket.getReview().getExhibitionTitle())
                .date(ticket.getReview().getDate())
                .ticketImageUrl(ticket.getTicketImageUrl())
                .build();
    }
}
