package com.example.demo.ticket.service;

import com.example.demo.common.exception.ExceptionCode;
import com.example.demo.common.exception.NotFoundException;
import com.example.demo.review.entity.Review;
import com.example.demo.review.repository.ReviewRepository;
import com.example.demo.s3.service.S3Service;
import com.example.demo.ticket.dto.TicketListResponseDto;
import com.example.demo.ticket.dto.TicketResponseDto;
import com.example.demo.ticket.entity.Ticket;
import com.example.demo.ticket.repository.TicketRepository;
import com.example.demo.user.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final S3Service s3Service;
    private final ReviewRepository reviewRepository;

    public TicketListResponseDto findTicketList(CustomUserDetails userDetails) {
        List<Ticket> tickets = ticketRepository.findByUser(userDetails.getUser());

        return TicketListResponseDto.from(tickets);
    }

    @Transactional
    public TicketResponseDto addTicket(
            CustomUserDetails userDetails,
            Long reviewId,
            String ticketImage
    ) throws IOException {
        String ticketImageUrl = ticketImage; //s3Service.saveFile(ticketImage);

        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new NotFoundException(ExceptionCode.NOT_FOUND_REVIEW)
        );

        Ticket ticket = Ticket.builder()
                .user(userDetails.getUser())
                .review(review)
                .ticketImageUrl(ticketImageUrl)
                .build();

        return TicketResponseDto.from(ticket);
    }
}
