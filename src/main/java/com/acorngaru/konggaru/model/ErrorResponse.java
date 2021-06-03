package com.acorngaru.konggaru.model;

import com.acorngaru.konggaru.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private int status;
    private String code;
    private String details;

    private ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.details = code.getDetails();
    }

    private ErrorResponse(ErrorCode code, String details) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.details = details;
    }

    public static ErrorResponse of(ErrorCode code) { return new ErrorResponse(code); }

    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult) {
        return new ErrorResponse(code, getDetailsFromBindingResult(bindingResult));
    }

    private static String getDetailsFromBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors()) {
                return bindingResult.getFieldErrors().get(0).getDefaultMessage();
            }
        }

        return "";
    }
}
