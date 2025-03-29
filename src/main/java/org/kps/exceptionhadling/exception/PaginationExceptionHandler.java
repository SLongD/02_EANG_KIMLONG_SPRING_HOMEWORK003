package org.kps.exceptionhadling.exception;

import lombok.Data;

@Data
public class PaginationExceptionHandler extends RuntimeException {
    private boolean invalidParameters;
    public PaginationExceptionHandler(String message) {
        super(message);
    }
    public PaginationExceptionHandler(String message, boolean invalidParameters) {
        super(message);
        this.invalidParameters = invalidParameters;
    }
    public PaginationExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public boolean isInvalidParameters() {
        return invalidParameters;
    }

}
