package com.example.todo_api.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_email", columnDefinition = "varchar(30)")
    private String email;

    @Column(name = "member_password", columnDefinition = "varchar(30)")
    private String password;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // 이메일 수정
    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }
    // 비밀번호 수정
    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}
