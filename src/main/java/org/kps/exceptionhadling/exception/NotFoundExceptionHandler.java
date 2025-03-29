package org.kps.exceptionhadling.exception;

public class NotFoundExceptionHandler extends RuntimeException {
    public NotFoundExceptionHandler(String message) {
        super(message);
    }
}
