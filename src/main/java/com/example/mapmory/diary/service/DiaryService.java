package com.example.mapmory.diary.service;

import com.example.mapmory.diary.domain.Diary;
import com.example.mapmory.member.domain.Member;
import com.example.mapmory.diary.dto.DiaryRequestDto;
import com.example.mapmory.diary.dto.DiaryResponseDto;
import com.example.mapmory.marker.dto.MarkerRequestDto;
import com.example.mapmory.marker.dto.MarkerResponseDto;
import com.example.mapmory.diary.repository.DiaryRepository;
import com.example.mapmory.member.repository.MemberRepository;
import com.example.mapmory.diary.service.S3UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private S3UploadService s3UploadService;

    @Transactional
    public Long saveMarker(MarkerRequestDto requestDto) {//이미지랑 위도경도 받고


        Member member = memberRepository.findById(requestDto.getMemberId()).get();
        Diary diary = diaryRepository.save(requestDto.toEntity(member));
        return diary.getId();
    }
    @Transactional
    public Long saveDiary(MultipartFile image, DiaryRequestDto requestDto) throws IOException {
        System.out.println("Diary service saveDiary");
        Member member = memberRepository.findById(requestDto.getMemberId()).get();


        if(!image.isEmpty()) {
            String storedFileName = s3UploadService.upload(image,"images");//storedfilename == url
            requestDto.setImageUrl(storedFileName);
        }
        //diary.setMember(memberRepository.findById(diary.getId()).get());//1117cnrk
        //Diary savedDiary = diaryRepository.save(diary);
        Diary savedDiary = diaryRepository.save(requestDto.toEntity(member));

        return savedDiary.getId();
    }
    public Optional<Diary> Update(Long id, Long idx, MultipartFile image ,DiaryRequestDto requestDto) throws IOException {
        System.out.println("Diary service saveDiary");

        Member member = memberRepository.findById(requestDto.getMemberId()).get();
        List<Diary> diaryList = diaryRepository.findByMember_Id(id);
        Diary updateDiary = diaryList.get(Math.toIntExact(idx-1));
        if(!image.isEmpty()) {
            String storedFileName = s3UploadService.upload(image,"images");//storedfilename == url
            requestDto.setImageUrl(storedFileName);
        }
        // 일단 idx에 맞는 값들을 찾아와
        Optional<Diary> entity = Optional.ofNullable(updateDiary);
        // ifPresent는 컨슈머를 매개변수로 입력받아서 객체가 존재할 때만 실행하는 Optional의 메소드입니다.
        entity.ifPresent(t ->{
            // 내용이 널이 아니라면 엔티티의 객체를 바꿔준다.
            if(requestDto.getContent() != null) {
                t.setContent(requestDto.getContent());
            }
            if(requestDto.getTitle() != null) {
                t.setTitle(requestDto.getTitle());
            }
            if(requestDto.getImageUrl() != null) {
                t.setImageUrl(requestDto.getImageUrl());
            }
            // 이걸 실행하면 idx 때문에 update가 실행됩니다.
            this.diaryRepository.save(t);
        });

        return entity;
    }

    public DiaryResponseDto getDiaryService(Long id, Long idx) {//id는 멤버의 id idx는 게시글의 순서(0부터 시ㅑㅇ)

        List<Diary> entityList = diaryRepository.findByMember_Id(id);
        Diary diary = entityList.get(Math.toIntExact(idx-1));

        DiaryResponseDto responseDto = new DiaryResponseDto();
        responseDto.setId(diary.getId());
        responseDto.setMember(diary.getMember());
        responseDto.setTitle(diary.getTitle());
        responseDto.setContent(diary.getContent());
        responseDto.setImageUrl(diary.getImageUrl());
        responseDto.setCreateDate(diary.getCreateDate());
        responseDto.setUpdatedDate(diary.getUpdatedDate());


        return responseDto;
    }
    public List<MarkerResponseDto> getMarker(Long id) {

        List<Diary> entityList = diaryRepository.findByMember_Id(id);
        List<MarkerResponseDto> result = new ArrayList<>();

        entityList.forEach(entity -> {
            MarkerResponseDto responseDto = new MarkerResponseDto();
            responseDto.setLatitude(entity.getLatitude());
            responseDto.setLongtitude(entity.getLongtitude());
            result.add(responseDto);
        });

        return result;
    }


}
