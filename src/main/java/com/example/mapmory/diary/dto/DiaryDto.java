package com.example.mapmory.diary.dto;

import com.example.mapmory.marker.domain.Marker;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;

public class DiaryDto {
    private Long id;
    private Marker marker;
    private String title;
    private String content;
    //private Long imageId;
    private LocalDateTime createDate;
    private LocalDateTime updatedDate;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
