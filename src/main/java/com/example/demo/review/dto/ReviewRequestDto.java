package com.example.demo.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewRequestDto {
    private Long exhibitionId;
    private String title;
    private LocalDate date;
    private String body;
    private List<MultipartFile> reviewImages;
}
