package org.orange.bookerz.api.controller.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member/hello")
public class MemberHelloController {

    @GetMapping("/")
    public String memberHello() {

        return "member hello";
    }

}
