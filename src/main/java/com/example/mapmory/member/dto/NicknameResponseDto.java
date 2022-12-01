package com.example.mapmory.member.dto;


import com.example.mapmory.member.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NicknameResponseDto {
    String nickname;


    public NicknameResponseDto(Member member){
        this.nickname =member.getNickname();
    }
}
