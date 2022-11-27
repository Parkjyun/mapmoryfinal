package com.example.mapmory.diary.dto;

import com.example.mapmory.member.domain.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DiaryResponseDto {
    private  Long id;

    private Member member;

    private String title;

    private String content;

    private String imageUrl;


    private LocalDate createDate;


    private LocalDate updatedDate;


}
