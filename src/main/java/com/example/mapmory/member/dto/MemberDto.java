package com.example.mapmory.member.dto;

import com.example.mapmory.member.domain.entity.Member;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "member")
@Data
public class MemberDto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
//유효성 검사에 필요한 Dto 객체에 Validation 어노테이션 사용
    private String nickname;

    private String email;

    private String password;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getMemberId() {
        return id;
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
                .id(id)
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
