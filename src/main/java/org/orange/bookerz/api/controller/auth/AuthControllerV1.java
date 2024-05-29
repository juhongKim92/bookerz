package org.orange.bookerz.api.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.orange.bookerz.api.controller.auth.request.SignInRequest;
import org.orange.bookerz.api.controller.auth.request.SignupRequest;
import org.orange.bookerz.api.service.auth.AuthService;
import org.orange.bookerz.api.service.auth.response.SignInResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/v1")
public class AuthControllerV1 {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signUp(@RequestBody @Valid SignupRequest request) {

        String email = request.getEmail();
        String password = request.getPassword();
        String passwordAgain = request.getPasswordAgain();
        String nickName = request.getNickName();

        authService.signUp(email, password, passwordAgain, nickName);
        return "signup success";
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest request) {

        String email = request.getEmail();
        String password = request.getPassword();

        SignInResponse response = authService.signIn(email, password);

        return ResponseEntity.ok().body(response);
    }
}
