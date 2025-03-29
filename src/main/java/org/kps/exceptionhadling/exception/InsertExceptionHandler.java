package org.kps.exceptionhadling.exception;

public class InsertExceptionHandler extends RuntimeException {

    public InsertExceptionHandler(String message) {
        super(message);
    }

    public InsertExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

}
