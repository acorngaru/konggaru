package com.acorngaru.konggaru.repository;

import com.acorngaru.konggaru.exception.AwsS3Exception;
import org.springframework.web.multipart.MultipartFile;

public interface S3Repository {

    String upload(MultipartFile image) throws AwsS3Exception;
}
