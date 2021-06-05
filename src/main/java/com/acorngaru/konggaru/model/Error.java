package com.acorngaru.konggaru.model;

import com.acorngaru.konggaru.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Error {
    private String message;
    private int status;
    private String code;
    private String details;

    private Error(ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.details = code.getDetails();
    }

    private Error(ErrorCode code, String details) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.details = details;
    }

    public static Error of(ErrorCode code) { return new Error(code); }

    public static Error of(ErrorCode code, BindingResult bindingResult) {
        return new Error(code, getDetailsFromBindingResult(bindingResult));
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
