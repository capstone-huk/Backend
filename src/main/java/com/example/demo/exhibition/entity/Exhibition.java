package com.example.demo.exhibition.entity;

import com.example.demo.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "exhibition")
public class Exhibition extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibition_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String placeAddr;

    @Column(nullable = false)
    private String realmName;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String placeUrl;
}
