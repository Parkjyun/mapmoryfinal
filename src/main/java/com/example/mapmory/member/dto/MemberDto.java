package com.example.mapmory.member.dto;

public class MemberDto {
    private Long memberId;

    private String nickname;

    private String memberIdStr;

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

    public String getMemberIdStr() {
        return memberIdStr;
    }

    public void setMemberIdStr(String memberId) {
        this.memberIdStr = memberIdStr;
    }
}
