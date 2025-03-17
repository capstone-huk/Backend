package com.example.demo.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequestDto {
    private String body;
    private Set<String> keepImageUrls;
    private List<MultipartFile> newReviewImages;
}
