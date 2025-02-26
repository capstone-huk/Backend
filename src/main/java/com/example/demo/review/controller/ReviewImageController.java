package com.example.demo.review.controller;

import com.example.demo.review.dto.ReviewImageResponseDto;
import com.example.demo.review.service.ReviewImageService;
import com.example.demo.user.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews/images")
public class ReviewImageController {
    private final ReviewImageService reviewImageService;

    @GetMapping("{reviewId}")
    public ResponseEntity<ReviewImageResponseDto> reviewImageList(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long reviewId
    ) {
        ReviewImageResponseDto reviewImageResponseDto = reviewImageService.findReviewImageList(userDetails, reviewId);

        return ResponseEntity.ok().body(reviewImageResponseDto);
    }

}
