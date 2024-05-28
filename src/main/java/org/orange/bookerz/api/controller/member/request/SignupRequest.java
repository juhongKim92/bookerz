package org.orange.bookerz.api.controller.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {

    String email;
    String password;
    String passwordAgain;
    String nickName;

    @Builder
    public SignupRequest(String email, String password,String passwordAgain, String nickName) {
        this.email = email;
        this.password = password;
        this.passwordAgain = passwordAgain;
        this.nickName = nickName;
    }
}
