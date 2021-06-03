package com.acorngaru.konggaru.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class InvalidValueException extends Exception {
    private BindingResult bindingResult;

    public InvalidValueException(String message) {
        super(message);
    }
    public InvalidValueException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
