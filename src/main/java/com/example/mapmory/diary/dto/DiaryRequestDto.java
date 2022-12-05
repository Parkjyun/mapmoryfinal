package com.example.mapmory.diary.dto;

import com.example.mapmory.diary.domain.Diary;
import com.example.mapmory.marker.domain.Marker;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//하는중
@Getter
@NoArgsConstructor
@Setter
public class DiaryRequestDto {

    private Long markerId;
    private String title;

    private String content;

    private String imageUrl;


    @Builder
    public DiaryRequestDto(Long memberId, String title, String content) {
        this.markerId = memberId;

        this.content = content;
        this.title = title;
    }

    //requestdto의 함수로 인스턴스를 리턴함
    public Diary toEntity(Marker marker) {
        return Diary.builder()
                .marker(marker)
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .build();
    }
}