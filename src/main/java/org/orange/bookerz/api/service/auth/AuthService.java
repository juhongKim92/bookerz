package org.orange.bookerz.api.service.auth;

import lombok.RequiredArgsConstructor;
import org.orange.bookerz.api.exception.AlreadyExistException;
import org.orange.bookerz.api.exception.InvalidRequestException;
import org.orange.bookerz.api.service.auth.response.SignInResponse;
import org.orange.bookerz.domain.member.Member;
import org.orange.bookerz.domain.member.MemberRepository;
import org.orange.bookerz.utils.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    public void signUp(String email, String password,String passwordAgain, String nickName) {

        if (findMemberByEmail(email).isPresent()) {
            throw new AlreadyExistException("Email is already exist.");
        }

        if (!checkPassword(password, passwordAgain)) {
            throw new InvalidRequestException("password","Passwords do not match. Please re-enter your password.");
        }

        Member member = Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickName(nickName)
                .role("ROLE_USER")
                .build();

        memberRepository.save(member);
    }

    public SignInResponse signIn(String email, String inputPassword) {

        Optional<Member> memberOptional = findMemberByEmail(email);
        if (memberOptional.isEmpty()) {
            throw new InvalidRequestException("email","email does not exist. Please re-enter your email.");
        }
        Member member = memberOptional.get();

        String savedPassword = member.getPassword();

        if (!passwordEncoder.matches(inputPassword, savedPassword)) {
            throw new InvalidRequestException("password","wrong password. Please re-enter your password.");

        }
        Long memberId = member.getId();
        String nickName = member.getNickName();
        String token = JwtUtils.generateToken(memberId,email,nickName, "ROLE_USER");
        return new SignInResponse(token);
    }

    private Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    private boolean checkPassword(String password, String passwordAgain) {
        return password.equals(passwordAgain);
    }


}
