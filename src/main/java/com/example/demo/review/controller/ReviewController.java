package com.example.demo.review.controller;

import com.example.demo.review.dto.*;
import com.example.demo.review.repository.ReviewRepository;
import com.example.demo.review.service.ReviewService;
import com.example.demo.user.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // 전시 리뷰 리스트 조회
    @GetMapping("")
    public ResponseEntity<List<ReviewListResponseDto>> getReviewList(@AuthenticationPrincipal CustomUserDetails userDetails){

        List<ReviewListResponseDto> reviews = reviewService.getReviewList(userDetails);

        return ResponseEntity.ok(reviews);
    }

    // 전시 리뷰 상세 조회
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDetailResponseDto> getReviewDetail(@PathVariable Long reviewId) {

        ReviewDetailResponseDto reviewDetail = reviewService.findReviewDetail(reviewId);
        return ResponseEntity.ok(reviewDetail);
    }

    // 전시 리뷰 등록
    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReviewResponseDto> addReview(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam("exhibitionId") String exhibitionId,
            @RequestParam("exhibitionTitle") String exhibitionTitle,
            @RequestParam("exhibitionImageURL") String exhibitionImageURL,
            @RequestParam("place") String place,
            @RequestParam("during") String during,
            @RequestParam("title") String title,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("body") String body,
            @RequestPart(value = "reviewImages", required = false) List<MultipartFile> reviewImages
    ) throws IOException {
        ReviewResponseDto responseDto = reviewService.addReview(
                userDetails, exhibitionId, exhibitionTitle, exhibitionImageURL,
                place, during, title, date, body, reviewImages
        );
        return ResponseEntity.ok(responseDto);
    }


    // 전시 리뷰 수정
    @PutMapping(path = "/{reviewId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReviewResponseDto> updateReview(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long reviewId, @ModelAttribute ReviewUpdateRequestDto requestDto) throws IOException {

        ReviewResponseDto responseDto = reviewService.updateReview(userDetails, reviewId, requestDto);

        return ResponseEntity.ok(responseDto);
    }


}
