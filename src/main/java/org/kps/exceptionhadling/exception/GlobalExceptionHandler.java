package org.kps.exceptionhadling.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundExceptionHandler.class)
    public ProblemDetail handleException(NotFoundExceptionHandler ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now());
        return detail;
    }
    @ExceptionHandler(InsertExceptionHandler.class)
    public ProblemDetail  handleException(InsertExceptionHandler ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now());
        return detail;
    }
    @ExceptionHandler(UpdateExceptionHandler.class)
    public ProblemDetail  handleException(UpdateExceptionHandler ex) {
        HttpStatus status = ex.isNotFound() ? HttpStatus.NOT_FOUND :
                HttpStatus.BAD_REQUEST;
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now());
        return detail;
    }
    @ExceptionHandler(PaginationExceptionHandler.class)
    public ProblemDetail  handleException(PaginationExceptionHandler ex) {
        HttpStatus status = ex.isInvalidParameters() ? HttpStatus.BAD_REQUEST :
                HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now());
        return detail;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail  handleException(MethodArgumentNotValidException ex) {
        Map<String , String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        String path = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest().getRequestURI();
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setStatus(400);
        detail.setProperty("timestamp", LocalDateTime.now());
        detail.setProperty("errors", errors);
        detail.setProperty("path",path);
        return detail;
    }
}
