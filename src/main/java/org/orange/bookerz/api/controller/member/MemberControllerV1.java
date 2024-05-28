package org.orange.bookerz.api.controller.member;

import lombok.RequiredArgsConstructor;
import org.orange.bookerz.api.controller.member.request.SignupRequest;
import org.orange.bookerz.api.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/v1")
public class MemberControllerV1 {

    private final MemberService memberService;

    @PostMapping("/signup")
    public void signUp(@RequestBody SignupRequest request) {

        String email = request.getEmail();
        String password = request.getPassword();
        String passwordAgain = request.getPasswordAgain();
        String nickName = request.getNickName();

        memberService.signUp(email,password,passwordAgain,nickName);
    }
}
