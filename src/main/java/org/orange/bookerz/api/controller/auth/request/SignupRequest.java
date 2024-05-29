package org.orange.bookerz.api.controller.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "email must not be blank")
    String email;
    @NotBlank(message = "password must not be blank")
    String password;
    @NotBlank(message = "password must not be blank")
    String passwordAgain;
    @NotBlank(message = "nickName must not be blank")
    String nickName;

    @Builder
    public SignupRequest(String email, String password,String passwordAgain, String nickName) {
        this.email = email;
        this.password = password;
        this.passwordAgain = passwordAgain;
        this.nickName = nickName;
    }
}
