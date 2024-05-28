package org.orange.bookerz.api.service;

import lombok.RequiredArgsConstructor;
import org.orange.bookerz.api.exception.EmailDuplicateException;
import org.orange.bookerz.api.exception.InvalidRequestException;
import org.orange.bookerz.domain.member.Member;
import org.orange.bookerz.domain.member.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    public void signUp(String email, String password,String passwordAgain, String nickName) {

        if (!isEmailAvailable(email)) {
            throw new EmailDuplicateException();
        }

        if (!checkPassword(password, passwordAgain)) {
            throw new InvalidRequestException("password","password wrong");
        }



    }


    private boolean isEmailAvailable (String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    private boolean checkPassword(String password, String passwordAgain) {
        return password.equals(passwordAgain);
    }


}
