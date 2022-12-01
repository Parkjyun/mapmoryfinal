package com.example.mapmory.marker.controller;

import com.example.mapmory.marker.dto.MarkerRequestDto;
import com.example.mapmory.marker.dto.MarkerResponseDto;
import com.example.mapmory.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MarkerController {
    private final DiaryService diaryService;

    @GetMapping(value = "{id}/markers" )//map, id는 멤버의 pk 11/19
    public ResponseEntity<List<MarkerResponseDto>> getMarkers(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(diaryService.getMarker(id));
    }
    @PostMapping("/marker/new")//json으로 위도, 경도 넣어주면 marker db저장 외래키매핑은? Id넘겨주 markerservice에서 id로 멤버 객체 찾아서 넣어줌
    public Long createMarker(@RequestBody MarkerRequestDto requestDto) {

        return diaryService.saveMarker(requestDto);

    }


}

