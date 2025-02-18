package com.example.demo.ticket.controller;

import com.example.demo.ticket.dto.TicketListResponseDto;
import com.example.demo.user.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
