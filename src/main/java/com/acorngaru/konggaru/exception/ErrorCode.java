package com.acorngaru.konggaru.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_VALUE(400, "C001", "요청 형식이 올바르지 않습니다.", "해당 양식에 맞게 입력 후 다시 시도해주세요."),

    PRODUCT_NAME_DUPLICATE(400, "P001", "상품명이 중복되었습니다.", "다른 이름으로 등록해주세요."),
    PRODUCT_NOT_FOUND(404, "P002", "해당 상품은 존재하지 않습니다.", "존재하는 상품을 입력해주세요."),

    S3_UPLOAD_ERROR(500, "S001", "이미지 업로드 중 문제가 발생하였습니다.", "잠시 후 다시 시도해주세요.");

    private final int status;
    private final String code;
    private final String message;
    private final String details;
}
