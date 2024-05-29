package org.orange.bookerz.api.service.auth.response;

import lombok.Getter;

@Getter
public class SignInResponse {
    private String token;

    public SignInResponse(String token) {
        this.token = token;
    }
}
