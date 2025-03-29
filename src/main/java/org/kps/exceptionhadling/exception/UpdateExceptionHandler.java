package org.kps.exceptionhadling.exception;

import lombok.Data;

@Data
public class UpdateExceptionHandler extends RuntimeException {
    private boolean notFound;
    public UpdateExceptionHandler(String message) {
        super(message);
    }
    public UpdateExceptionHandler(String message, boolean notFound) {
        super(message);
        this.notFound = notFound;
    }
    public UpdateExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public boolean isNotFound() {
        return notFound;
    }

}
