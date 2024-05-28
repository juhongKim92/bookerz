package org.orange.bookerz.api.controller;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

public record ErrorResponse(int code, String message, Map<String, String> validation) {

    @Builder
    public ErrorResponse {
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public Map<String, String> validation() {
        return validation;
    }
}
