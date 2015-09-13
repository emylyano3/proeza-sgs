package com.proeza.core.persistence.exception;

public class QueryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public QueryNotFoundException (String message, Throwable cause) {
        super(message, cause);
    }

    public QueryNotFoundException (String message) {
        super(message);
    }
}