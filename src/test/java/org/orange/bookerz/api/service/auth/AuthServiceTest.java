package org.orange.bookerz.api.service.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.orange.bookerz.api.exception.AlreadyExistException;
import org.orange.bookerz.api.exception.InvalidRequestException;
import org.orange.bookerz.api.service.auth.AuthService;
import org.orange.bookerz.domain.member.Member;
import org.orange.bookerz.domain.member.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    MemberRepository memberRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    AuthService authService;

    @BeforeEach
    void setUp(){
//        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("회원 가입시 이메일이 이미 존재하는 경우 에러 발생")
    void throw_exception_if_email_exists_when_signup() throws Exception{
        // given
        String email = "test@email.com";
        // when
        when(memberRepository.findByEmail(email)).thenReturn(Optional.of(new Member()));

        // then
        assertThrows(AlreadyExistException.class, () -> {
            authService.signUp(email, "password", "password", "nickName");
        });
    }

    @Test
    @DisplayName("회원 가입시 비밀번호와 확인용 비빌먼호가 다르면 에러 발생")
    void throw_exception_when_password_do_not_match() throws Exception {
        // given
        String email = "test@email.com";
        // when
        when(memberRepository.findByEmail(email)).thenReturn(Optional.empty());

        // then
        assertThrows(InvalidRequestException.class, () -> {
            authService.signUp(email, "password1", "password2", "nickname");
        });
    }

    @Test
    @DisplayName("회원가입 성공")
    void signup_success() throws Exception {
        // given
        String email = "test@email.com";
        String password = "password";
        String nickName = "nickName";

        // when
        when(memberRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        authService.signUp(email, password, password, nickName);

        // then
        verify(memberRepository).save(any(Member.class));
    }
}