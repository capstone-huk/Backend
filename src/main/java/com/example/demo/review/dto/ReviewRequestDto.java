package com.example.demo.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDto {
    private String exhibitionId;
    private String exhibitionTitle;
    private String exhibitionImageURL;
    private String place;
    private String during;
    private String title;
    private LocalDate date;
    private String body;
    private List<MultipartFile> reviewImages;
}
