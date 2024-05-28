package org.orange.bookerz.api.exception;

public class EmailDuplicateException extends BaseException {

    private static final String MESSAGE = "Email is already exist.";

    public EmailDuplicateException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
