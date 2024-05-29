package org.orange.bookerz.domain.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(unique = true)
    String email;
    String password;
    @Column(unique = true)
    String nickName;
    String role;
    @Builder
    public Member(String email, String password, String nickName, String role) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.role = role;
    }
}
