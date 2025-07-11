package com.example.demo.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class ReviewDetailResponseDto {
    private String exhibitionSeq;
    private String exhibitionTitle;
    private String place;
    private String during;
    private String exhibitionImageURL;
    private LocalDate date;
    private String body;
    private List<String> reviewImages;

}
