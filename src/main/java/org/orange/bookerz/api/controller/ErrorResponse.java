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


}
