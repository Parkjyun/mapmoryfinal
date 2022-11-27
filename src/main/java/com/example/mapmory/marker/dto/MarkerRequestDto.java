package com.example.mapmory.marker.dto;

import com.example.mapmory.diary.domain.Diary;
import com.example.mapmory.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MarkerRequestDto {

    private Long memberId;
    private Double longtitude;
    private Double latitude;

    @Builder
    public MarkerRequestDto(Long memberId, Double longtitude, Double latitude) {
        this.memberId = memberId;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    //requestdto의 함수로 인스턴스를 리턴함
    public Diary toEntity(Member member) {
        return Diary.builder()
                .member(member)
                .latitude(latitude)
                .longtitude(longtitude)
                .build();
    }
}
