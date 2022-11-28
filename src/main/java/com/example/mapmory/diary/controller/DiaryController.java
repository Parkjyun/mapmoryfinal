package com.example.mapmory.diary.controller;

import com.example.mapmory.diary.domain.Diary;
import com.example.mapmory.diary.dto.DiaryRequestDto;
import com.example.mapmory.diary.dto.DiaryResponseDto;
import com.example.mapmory.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class DiaryController {
    private final DiaryService diaryService;
    /*@ResponseBody   // Long 타입을 리턴하고 싶은 경우 붙여야 함 (Long - 객체)
    @PostMapping(value = "/marker/diary/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long saveDiary(HttpServletRequest request, @RequestParam(value = "image") MultipartFile image, DiaryRequestDto requestDto) throws IOException {
        System.out.println("DiaryController.saveDiary");
        System.out.println(image);

        System.out.println("------------------------------------------------------");
        Long diaryId = diaryService.saveDiary(image, requestDto);
        return diaryId;
    }
    */

    @PutMapping(value = "{id}/marker/{idx}/diary", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Diary> diaryUpdate(HttpServletRequest request, @RequestParam(value = "image") MultipartFile image, DiaryRequestDto requestDto,@PathVariable() Long idx,@PathVariable() Long id) throws IOException {
        //디코딩
        Optional<Diary> diary = this.diaryService.Update(id, idx, image ,requestDto);

        return new ResponseEntity(diary, HttpStatus.OK);
    }
    @GetMapping(value = "{id}/marker/{idx}/diary" )
    public DiaryResponseDto getDiary(@PathVariable() Long idx, @PathVariable() Long id) {
        DiaryResponseDto diaryResponseDto = new DiaryResponseDto();
        diaryResponseDto = diaryService.getDiaryService(id, idx);
        return diaryResponseDto;
    }

}
