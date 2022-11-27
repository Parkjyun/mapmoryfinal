package com.example.mapmory.member.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(name = "member_id")
    private String memberId;

    public Member(String memberId, String nickname) {
        this.nickname = nickname;
        this.memberId = memberId;
    }
}