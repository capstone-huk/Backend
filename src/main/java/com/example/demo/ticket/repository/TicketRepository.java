package com.example.demo.ticket.repository;

import com.example.demo.ticket.entity.Ticket;
import com.example.demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);
    List<Ticket> findByUserOrderByCreatedAtDesc(User user);
}
