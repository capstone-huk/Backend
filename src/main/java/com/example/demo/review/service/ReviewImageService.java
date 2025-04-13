package com.example.demo.review.service;

import com.example.demo.common.exception.ExceptionCode;
import com.example.demo.common.exception.NotFoundException;
import com.example.demo.review.dto.ReviewImageResponseDto;
import com.example.demo.review.entity.Review;
import com.example.demo.review.repository.ReviewImageRepository;
import com.example.demo.review.repository.ReviewRepository;
import com.example.demo.user.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ReviewImageService {
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    public ReviewImageResponseDto findReviewImageList(
            CustomUserDetails userDetails,
            Long reviewId
    ) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new NotFoundException(ExceptionCode.NOT_FOUND_REVIEW)
        );

        List<String> urls = reviewImageRepository.findReviewImageUrlByReview(review);

        return ReviewImageResponseDto.builder()
                .reviewImages(urls)
                .reviewId(reviewId)
                .exhibitionId(review.getExhibition().getId())
                .exhibitionTitle(review.getExhibition().getTitle())
                .build();
    }
}
