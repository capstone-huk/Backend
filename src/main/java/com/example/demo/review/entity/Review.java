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
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "exhibition_id", nullable = true)
    private Exhibition exhibition;

    @Column(nullable = true)
    private String exhibitionSeq;

    @Column(nullable = false)
    private String exhibitionTitle;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String during;

    @Column(nullable = false)
    private String exhibitionImageURL;

    @Column(nullable = true)
    private LocalDate date;

    @Column(nullable = true)
    private String body;

    public void setBody(String body) {
        this.body = body;
    }
}
