package com.acorngaru.konggaru.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Slf4j
@Repository
@RequiredArgsConstructor
public class S3RepositoryImpl implements S3Repository {
    private final AmazonS3 s3Client;

    @Value("${s3.bucketName}")
    private String bucketName;

    @Override
    public String upload(MultipartFile image) throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fileExtension = "." + image.getName().split("\\.")[1];
        String filePath = "images/" + timestamp.getTime() + fileExtension;

        s3Client.putObject(
                new PutObjectRequest(bucketName, filePath, image.getInputStream(), null)
        );

        log.info("upload() - Uploaded at {}", s3Client.getUrl(bucketName, filePath));

        return s3Client.getUrl(bucketName, filePath).toString();
    }
}
