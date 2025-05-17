package com.example.demo.review.entity;

import com.example.demo.common.entity.BaseTimeEntity;
import com.example.demo.exhibition.entity.Exhibition;
import com.example.demo.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Builder
@Table(name = "review")
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "exhibition_id", nullable = false)
    private Long exhibitionId;

    @Column(nullable = true)
    private LocalDate date;

    @Column(nullable = true)
    private String body;

    public void setBody(String body) {
        this.body = body;
    }

    @Builder
    public Review(Long id, User user, Long exhibitionId, LocalDate date, String body) {
        this.id = id;
        this.user = user;
        this.exhibitionId = exhibitionId;
        this.date = date;
        this.body = body;
    }

}
