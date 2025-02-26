package com.example.demo.exhibition.repository;

import com.example.demo.exhibition.entity.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    Optional<Exhibition> findById(Long exhibitionId);
}
