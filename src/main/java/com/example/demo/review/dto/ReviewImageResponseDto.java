package com.example.demo.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImageResponseDto {
    private String exhibitionSeq;
    private String exhibitionTitle;
    private Long reviewId;
    private List<String> reviewImages;
}
