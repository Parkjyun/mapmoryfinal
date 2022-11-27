package com.example.mapmory.marker.dto;

//이름은 markeResponseDto이지만 사실 다이어리에서 위경도 뽑아서 json 뿌려주는 용도

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarkerResponseDto {
    private Double longtitude;

    private Double latitude;


}
