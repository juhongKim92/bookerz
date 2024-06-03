package org.orange.bookerz.api.controller.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/member/hello")
public class MemberHelloController {

    @GetMapping("/")
    public String memberHello() {
//        log.info("username : {}",user.getUsername());
        return "member hello";
    }

}
