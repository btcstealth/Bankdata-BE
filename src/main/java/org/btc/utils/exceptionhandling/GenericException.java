package org.btc.utils.exceptionhandling;

import java.io.Serializable;

public class GenericException extends RuntimeException implements Serializable {
    private ErrorCode errorCode;

    public GenericException() {
    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public GenericException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
