package com.example.mapmory.diary.service;

import com.example.mapmory.diary.domain.Diary;
import com.example.mapmory.marker.domain.Marker;
import com.example.mapmory.marker.repository.MarkerRepository;
import com.example.mapmory.member.domain.entity.Member;
import com.example.mapmory.diary.dto.DiaryRequestDto;
import com.example.mapmory.diary.dto.DiaryResponseDto;
import com.example.mapmory.marker.dto.MarkerRequestDto;
import com.example.mapmory.marker.dto.MarkerResponseDto;
import com.example.mapmory.diary.repository.DiaryRepository;
import com.example.mapmory.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MarkerRepository markerRepository;
    @Autowired
    private S3UploadService s3UploadService;

    @Transactional
    public Long saveMarker(MarkerRequestDto requestDto) {


        Member member = memberRepository.findById(requestDto.getMemberId()).get();
        Marker marker = markerRepository.save(requestDto.toEntity(member));
        return marker.getId();
    }
    @Transactional
    public Long saveDiary(MultipartFile image, DiaryRequestDto requestDto) throws IOException {
        System.out.println("Diary service saveDiary");
        Marker marker = markerRepository.findById(requestDto.getMarkerId()).get();


        if(!image.isEmpty()) {
            String storedFileName = s3UploadService.upload(image,"images");//storedfilename == url
            requestDto.setImageUrl(storedFileName);
        }
        //diary.setMember(memberRepository.findById(diary.getId()).get());//1117cnrk
        //Diary savedDiary = diaryRepository.save(diary);
        Diary savedDiary = diaryRepository.save(requestDto.toEntity(marker));

        return savedDiary.getId();
    }
    public Diary create(Long id, Long idx, MultipartFile image ,DiaryRequestDto requestDto) throws IOException {
        System.out.println("Diary service saveDiary");

/*
        List<Marker> markerList = markerRepository.findByMember_Id(id);
        //List<Diary> entityList = diaryRepository.findByMarker_Id(idx);
        Marker marker = markerList.get(Math.toIntExact(idx-1));
*/
        //12/1바꾼 부분 마커를 그냥 idx로 바꾸면 되지 않아?

        Marker marker = markerRepository.findById(idx).get();



        if(!image.isEmpty()) {
            String storedFileName = s3UploadService.upload(image,"images");//storedfilename == url
            requestDto.setImageUrl(storedFileName);
        }
        // 일단 idx에 맞는 값들을 찾아와

        Diary diary = diaryRepository.save(requestDto.toEntity(marker));


        return diary;
    }

    public DiaryResponseDto getDiaryService(Long id, Long idx) {
        List<Marker> markerList = markerRepository.findByMember_Id(id);
        //List<Diary> entityList = diaryRepository.findByMarker_Id(idx);
      //  Marker marker = markerList.get(Math.toIntExact(idx-1));
        Marker marker = markerRepository.findById(idx).get();
        Diary diary =diaryRepository.findByMarker_Id(marker.getId()).get(0);


        //Diary diary = entityList.get(Math.toIntExact(idx-1));

        DiaryResponseDto responseDto = new DiaryResponseDto();
        responseDto.setId(diary.getId());
        responseDto.setMember(diary.getMarker().getMember());
        responseDto.setTitle(diary.getTitle());
        responseDto.setContent(diary.getContent());
        responseDto.setImageUrl(diary.getImageUrl());
        responseDto.setCreateDate(diary.getCreateDate());
        responseDto.setUpdatedDate(diary.getUpdatedDate());


        return responseDto;
    }
    public List<MarkerResponseDto> getMarker(Long id) {

        List<Marker> entityList = markerRepository.findByMember_Id(id);
        List<MarkerResponseDto> result = new ArrayList<>();

        entityList.forEach(entity -> {
            MarkerResponseDto responseDto = new MarkerResponseDto();
            responseDto.setMarkerId(entity.getId());
            responseDto.setLat(entity.getLatitude());
            responseDto.setLng(entity.getLongtitude());
            result.add(responseDto);
        });

        return result;
    }

    public List<DiaryResponseDto> getDiary(Long idx) {
        List<Diary> entityList = diaryRepository.findByMarker_Id(idx);
        List<DiaryResponseDto> result = new ArrayList<>();

        entityList.forEach(entity -> {
            DiaryResponseDto responseDto = new DiaryResponseDto();
            responseDto.setId(entity.getId());
            responseDto.setCreateDate(entity.getCreateDate());
            responseDto.setImageUrl(entity.getImageUrl());
            responseDto.setContent(entity.getContent());
            responseDto.setTitle(entity.getTitle());
            result.add(responseDto);
        });

        return result;

    }


    //public List<DiaryResponseDto> getDiarys(Long id, Long idx) {

    //}
}
