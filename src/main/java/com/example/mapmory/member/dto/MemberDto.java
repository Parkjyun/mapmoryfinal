package com.example.mapmory.member.dto;

import com.example.mapmory.member.domain.entity.Member;
import lombok.Getter;

@Getter
public class MemberDto {
    private Long memberId;
//유효성 검사에 필요한 Dto 객체에 Validation 어노테이션 사용
    private String nickname;

    private String email;

    private String password;
    public Long getId() {
        return memberId;
    }

    public void setId(Long id) {
        this.memberId = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
