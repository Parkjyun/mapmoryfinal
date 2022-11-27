package com.example.mapmory.diary.dto;

import com.example.mapmory.diary.domain.Diary;
import com.example.mapmory.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//하는중
@Getter
@NoArgsConstructor
@Setter
public class DiaryRequestDto {

    private Long memberId;
    private String title;

    private String content;

    private String imageUrl;


    @Builder
    public DiaryRequestDto(Long memberId, String title, String content) {
        this.memberId = memberId;

        this.content = content;
        this.title = title;
    }

    //requestdto의 함수로 인스턴스를 리턴함
    public Diary toEntity(Member member) {
        return Diary.builder()
                .member(member)
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .build();
    }
}