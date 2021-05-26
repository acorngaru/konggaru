package com.acorngaru.konggaru.repository;

import org.springframework.web.multipart.MultipartFile;

public interface S3Repository {
    String upload(MultipartFile image) throws Exception;

}
