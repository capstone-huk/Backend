package com.example.demo.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 amazonS3;

    @Value("${BUCKET_NAME}")
    private String bucketName;

    public String saveFile(MultipartFile file) throws IOException {
        try {
            String originalFilename = file.getOriginalFilename();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            amazonS3.putObject(bucketName, originalFilename, file.getInputStream(), metadata);
            return amazonS3.getUrl(bucketName, originalFilename).toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


    }
}
