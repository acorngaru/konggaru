package com.acorngaru.konggaru.exception;

import com.acorngaru.konggaru.model.Error;
import com.acorngaru.konggaru.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Validator 통해 바인딩 오류가 발생한 경우
     */
    @ExceptionHandler(InvalidValueException.class)
    @ResponseBody
    public Response<Error> handleInvalidValueException(InvalidValueException e) {
        log.error("InvalidValueException: {}", e.getMessage());
        Error error = Error.of(ErrorCode.INVALID_VALUE, e.getBindingResult());

        return Response.ERROR(error);
    }

    /**
     * 각 컬렴별 양식이 맞지 않거나, 제약 조건을 위배한 경우
     */
    @ExceptionHandler(MyBatisSystemException.class)
    @ResponseBody
    public Response<Error> handleMyBatisSystemException(MyBatisSystemException e) {
        log.error("MyBatisSystemException: {}", e.getMessage());
        Error error = Error.of(ErrorCode.INVALID_VALUE);

        return Response.ERROR(error);
    }

    /**
     * AWS S3 이미지 업로드 중 오류가 발생한 경우
     */
    @ExceptionHandler(AwsS3Exception.class)
    @ResponseBody
    public Response<Error> handleAwsS3Exception(AwsS3Exception e) {
        log.error("AwsS3Exception: {}", e.getMessage());
        Error error = Error.of(ErrorCode.S3_UPLOAD_ERROR);

        return Response.ERROR(error);
    }

    /**
     * 해당 컬럼 값이 존재하는 경우
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public Response<Error> handleDuplicateKeyException(DuplicateKeyException e) {
        Error error = Error.of(ErrorCode.PRODUCT_NAME_DUPLICATE);

        return Response.ERROR(error);
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
     * 잘못된 주소로 접근한 경우
     */
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException e, Model m) {
        m.addAttribute("error", e);

        return "error/403";
    }

    /**
     * 실행 중 오류가 발생한 경우
     */
    @ExceptionHandler(Exception.class)
    public String handleException(RuntimeException e, Model m) {
        m.addAttribute("error", e);

        return "/error/500";
    }
}
