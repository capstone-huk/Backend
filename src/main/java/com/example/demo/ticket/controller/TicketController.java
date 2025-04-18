package com.example.demo.ticket.controller;

import com.example.demo.ticket.dto.TicketListResponseDto;
import com.example.demo.ticket.dto.TicketRequestDto;
import com.example.demo.ticket.dto.TicketResponseDto;
import com.example.demo.ticket.service.TicketService;
import com.example.demo.user.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("")
    public ResponseEntity<TicketListResponseDto> ticketList(
            @AuthenticationPrincipal CustomUserDetails userDetails
            ) {
        TicketListResponseDto ticketList = ticketService.findTicketList(userDetails);

        return ResponseEntity.ok().body(ticketList);
    }

    @PostMapping(path = "")
    public ResponseEntity<TicketResponseDto> ticketAdd(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody TicketRequestDto ticketRequestDto
    ) throws IOException {
        TicketResponseDto ticketResponseDto = ticketService.addTicket(
                userDetails,
                ticketRequestDto
        );

        return ResponseEntity.ok().body(ticketResponseDto);
    }
}
