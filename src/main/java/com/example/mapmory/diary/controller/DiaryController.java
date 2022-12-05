package com.example.mapmory.diary.controller;

import com.example.mapmory.diary.domain.Diary;
import com.example.mapmory.diary.dto.DiaryRequestDto;
import com.example.mapmory.diary.dto.DiaryResponseDto;
import com.example.mapmory.diary.service.DiaryService;
import com.example.mapmory.marker.dto.MarkerResponseDto;
import com.example.mapmory.marker.service.MarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class DiaryController {
    private final DiaryService diaryService;

    private final MarkerService markerService;
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

    @PostMapping(value = "{id}/marker/{idx}/diary", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)//이미지가
    public ResponseEntity<List<MarkerResponseDto>> createDiary(HttpServletRequest request, @RequestParam(value = "image") MultipartFile image,
                                                               DiaryRequestDto requestDto, @PathVariable() Long idx, @PathVariable() Long id) throws IOException {
        //디코딩
        Diary diary = this.diaryService.create(id, idx, image ,requestDto);

        return ResponseEntity.ok()
                .body(diaryService.getMarker(id));
    }
    /*
    @GetMapping(value = "{id}/marker/{idx}/diary" )
    public DiaryResponseDto getDiary(@PathVariable() Long idx, @PathVariable() Long id) {

        DiaryResponseDto responseDto = diaryService.getDiaryService(id, idx);

        return responseDto;
    }
    */


    //다이어리가 여러개일 때 여러개의 다이어리 갖고오는 method
    @GetMapping(value = "{id}/marker/{idx}/diary")
    public ResponseEntity<List<DiaryResponseDto>> getDiary(@PathVariable Long id, @PathVariable Long idx) {
        return ResponseEntity.ok()
                .body(diaryService.getDiary(idx));
    }

    @DeleteMapping(value = "{id}/marker/{idx}/diary")
    public ResponseEntity<List<MarkerResponseDto>> deleteDiary(@PathVariable() Long idx, @PathVariable() Long id) {

        Long deletedMarkerId = markerService.deleteMarker(id, idx);




        return ResponseEntity.ok()
                .body(diaryService.getMarker(id));
    }
}
