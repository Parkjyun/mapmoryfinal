package com.example.mapmory.marker.service;

import com.example.mapmory.marker.repository.MarkerRepository;
import com.example.mapmory.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarkerService {

    private final MemberRepository memberRepository;
    private final MarkerRepository markerRepository;

   /* @Transactional
    public Long save(MarkerRequestDto requestDto) {//이미지랑 위도경도 받고


        Member member = memberRepository.findById(requestDto.getMemberId()).get();
        Marker marker = markerRepository.save(requestDto.toEntity(member));
        return marker.getId();
    }
    */

}
