package org.orange.bookerz.api.exception;

import lombok.Getter;

@Getter
public class InvalidRequestException extends BaseException {

    private static final String MESSAGE = "invalid request";

    public InvalidRequestException() {
        super(MESSAGE);
    }
    public InvalidRequestException(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }
    @Override
    public int getStatusCode() {
        return 400;
    }

}
