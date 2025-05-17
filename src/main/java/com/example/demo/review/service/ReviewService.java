package com.example.demo.review.service;

import com.example.demo.common.exception.ExceptionCode;
import com.example.demo.common.exception.NotFoundException;
import com.example.demo.exhibition.entity.Exhibition;
import com.example.demo.review.dto.*;
import com.example.demo.review.entity.Review;
import com.example.demo.review.entity.ReviewImage;
import com.example.demo.review.repository.ReviewImageRepository;
import com.example.demo.review.repository.ReviewRepository;
import com.example.demo.s3.service.S3Service;
import com.example.demo.user.entity.CustomUserDetails;
import com.example.demo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.exhibition.repository.ExhibitionRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final S3Service s3Service;

    public List<ReviewListResponseDto> getReviewList(CustomUserDetails userDetails) {

        User user = userDetails.getUser();
        List<Review> reviews = reviewRepository.findByUserId(user);

        return reviews.stream()
                .map(review -> {
                    List<String> urls = reviewImageRepository.findReviewImageUrlByReview(review);
                    return ReviewListResponseDto.builder()
                            .reviewId(review.getId())
                            .exhibitionId(review.getExhibitionId())  // 수정
                            .date(review.getDate())
                            .body(review.getBody())
                            .reviewImages(urls)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public ReviewDetailResponseDto findReviewDetail(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_REVIEW));

        List<String> urls = reviewImageRepository.findReviewImageUrlByReview(review);

        return ReviewDetailResponseDto.builder()
                .exhibitionId(review.getExhibitionId())  // 수정
                .date(review.getDate())
                .body(review.getBody())
                .reviewImages(urls)
                .build();
    }

    @Transactional
    public ReviewResponseDto addReview(CustomUserDetails userDetails, ReviewRequestDto requestDto) throws IOException {
        User user = userDetails.getUser();

        Review review = Review.builder()
                .user(user)
                .exhibitionId(requestDto.getExhibitionId())
                .date(requestDto.getDate())
                .body(requestDto.getBody())
                .build();
        reviewRepository.save(review);

        List<ReviewImage> reviewImages = new ArrayList<>();
        if (requestDto.getReviewImages() != null) {
            for (MultipartFile image : requestDto.getReviewImages()) {
                String imageUrl = s3Service.saveFile(image); // S3에 저장 후 URL 반환

                ReviewImage reviewImage = ReviewImage.builder()
                        .review(review)
                        .imageUrl(imageUrl)
                        .build();
                reviewImages.add(reviewImage);
            }
            reviewImageRepository.saveAll(reviewImages);
        }

        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .build();
    }

    @Transactional
    public ReviewResponseDto updateReview(CustomUserDetails userDetails, Long reviewId, ReviewUpdateRequestDto requestDto) throws IOException {
        User user = userDetails.getUser();

        // 기존 리뷰 조회 (없으면 예외 발생)
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new NotFoundException(ExceptionCode.NOT_FOUND_REVIEW)
        );

        // 본문 수정
        if (requestDto.getBody() != null) {
            review.setBody(requestDto.getBody());
        }

        // 기존 이미지 리스트 가져오기
        List<String> existingImageUrls = reviewImageRepository.findReviewImageUrlByReview(review);

        // 사용자가 유지하고 싶은 이미지 목록이 있는 경우
        Set<String> keepImageUrls = requestDto.getKeepImageUrls();

        // 삭제할 이미지 URL 목록 계산
        Set<String> deleteImageUrls = existingImageUrls.stream()
                .filter(url -> !keepImageUrls.contains(url))
                .collect(Collectors.toSet());

        // S3에서 이미지 삭제 + DB에서 삭제
        if (!deleteImageUrls.isEmpty()) {
            deleteImageUrls.forEach(s3Service::deleteFile);
            reviewImageRepository.deleteByImageUrlIn(deleteImageUrls);
        }

        // 새로운 이미지 추가
        if (requestDto.getNewReviewImages() != null && !requestDto.getNewReviewImages().isEmpty()) {
            for (MultipartFile image : requestDto.getNewReviewImages()) {
                String imageUrl = s3Service.saveFile(image); // S3에 업로드 후 URL 반환

                ReviewImage reviewImage = ReviewImage.builder()
                        .review(review)
                        .imageUrl(imageUrl)
                        .build();
                reviewImageRepository.save(reviewImage);
            }
        }

        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .build();
    }

}
