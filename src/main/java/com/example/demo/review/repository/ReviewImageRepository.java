package com.example.demo.review.repository;

import com.example.demo.review.entity.Review;
import com.example.demo.review.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

    @Query("SELECT ri.imageUrl FROM ReviewImage ri WHERE ri.review =:review")
    List<String> findReviewImageUrlByReview(@Param("review") Review review);
}
