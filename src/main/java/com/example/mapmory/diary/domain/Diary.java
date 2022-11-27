package com.example.mapmory.diary.domain;

import com.example.mapmory.member.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
//자동 시간 입력을 위한 어노테이션, MapmoryApplication class에 있는 어노테이션과 연관
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

    private String imageUrl;

    private Double longtitude;

    private Double latitude;

    @CreatedDate
    private LocalDate createDate;

    @LastModifiedDate
    private LocalDate updatedDate;

    @Builder
    public Diary(Member member, String title, String content, String imageUrl, Double latitude, Double longtitude) {

        this.member = member;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}

