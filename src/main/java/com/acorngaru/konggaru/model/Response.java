package com.acorngaru.konggaru.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class Response<T> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime transactionTime;
    private String status;
    private T data;

    public static <T> Response<T> OK() {
        return (Response<T>) Response.builder()
                .transactionTime(LocalDateTime.now())
                .status("OK")
                .build();
    }

    public static <T> Response<T> OK(T data) {
        return (Response<T>) Response.builder()
                .transactionTime(LocalDateTime.now())
                .status("OK")
                .data(data)
                .build();
    }

    public static <T> Response<T> ERROR() {
        return (Response<T>) Response.builder()
                .transactionTime(LocalDateTime.now())
                .status("ERROR")
                .build();
    }

    public static <T> Response<T> ERROR(T error) {
        return (Response<T>) Response.builder()
                .transactionTime(LocalDateTime.now())
                .status("ERROR")
                .data(error)
                .build();
    }
}
