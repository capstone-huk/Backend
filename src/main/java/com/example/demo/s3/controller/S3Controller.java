package com.example.demo.s3.controller;

import com.example.demo.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class S3Controller {
    private final S3Service s3Service;

    @PostMapping("")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String filepath = s3Service.saveFile(file);

        return ResponseEntity.ok().body(filepath);
    }
}

