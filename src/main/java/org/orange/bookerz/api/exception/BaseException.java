package org.orange.bookerz.api.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class BaseException extends RuntimeException{
    public final Map<String, String> validation = new HashMap<>();

    protected BaseException(String message) {
        super(message);
    }

    protected BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();
    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
