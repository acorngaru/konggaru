package com.acorngaru.konggaru.exception;

import com.acorngaru.konggaru.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Validator 통해 바인딩 오류가 발생한 경우
     */
    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ErrorResponse> handleInvalidValueException(InvalidValueException e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_VALUE, e.getBindingResult());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * 각 컬렴별 양식이 맞지 않거나, 제약 조건을 위배한 경우
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public ResponseEntity<ErrorResponse> handleMyBatisSystemException(MyBatisSystemException e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_VALUE);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * AWS S3 이미지 업로드 중 오류가 발생한 경우
     */
    @ExceptionHandler(AwsS3Exception.class)
    public ResponseEntity<ErrorResponse> handleAwsS3Exception(AwsS3Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.S3_UPLOAD_ERROR);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * 해당 컬럼 값이 존재하는 경우
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateKeyException(DuplicateKeyException e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.PRODUCT_NAME_DUPLICATE);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * 잘못된 주소로 접근한 경우
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException e, Model m) {
        m.addAttribute("error", e);

        return "error/404";
    }

    /**
     * 실행 중 오류가 발생한 경우
     */
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, Model m) {
        m.addAttribute("error", e);

        return "error/500";
    }
}
