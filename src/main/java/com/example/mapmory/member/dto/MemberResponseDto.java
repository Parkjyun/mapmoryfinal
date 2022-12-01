package com.example.mapmory.member.dto;

import com.example.mapmory.member.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;


    public MemberResponseDto(Member member){
        this.id = member.getId();

    }
}
