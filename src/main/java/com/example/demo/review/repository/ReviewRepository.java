package com.example.demo.review.repository;

import com.example.demo.review.entity.Review;
import com.example.demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long id);
    List<Review> findByUserId(Long userId);

    Long user(User user);
}
