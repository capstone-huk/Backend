package com.example.demo.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListResponseDto {
    private Long reviewId;
    private Long exhibitionId;
    private LocalDate date;
    private String body;
    private List<String> reviewImages;
}
