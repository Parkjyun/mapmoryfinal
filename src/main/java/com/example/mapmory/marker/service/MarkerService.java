package com.example.mapmory.marker.service;

import com.example.mapmory.marker.domain.Marker;
import com.example.mapmory.marker.dto.MarkerRequestDto;
import com.example.mapmory.marker.repository.MarkerRepository;
import com.example.mapmory.member.domain.entity.Member;
import com.example.mapmory.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkerService {

    private final MemberRepository memberRepository;
    private final MarkerRepository markerRepository;

   @Transactional
    public Long save(MarkerRequestDto requestDto) {//이미지랑 위도경도 받고


        Member member = memberRepository.findById(requestDto.getMemberId()).get();
        Marker marker = markerRepository.save(requestDto.toEntity(member));
        return marker.getId();
    }

    @Transactional
    public Long deleteMarker(Long id, Long idx) {

    Marker deleteMarker = markerRepository.findById(idx).get();
    markerRepository.delete(deleteMarker);

    return deleteMarker.getId();

    }

}
