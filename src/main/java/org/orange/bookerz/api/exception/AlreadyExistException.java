package org.orange.bookerz.api.exception;

public class AlreadyExistException extends BaseException {

    private static final String MESSAGE = "Object is already exist.";

    public AlreadyExistException() {
        super(MESSAGE);
    }

    public AlreadyExistException(String message) {
        super(message);
    }
    @Override
    public int getStatusCode() {
        return 400;
    }
}
