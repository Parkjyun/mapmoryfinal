package com.example.mapmory.diary.repository;

import com.example.mapmory.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {//diary는 repository의 class 타입, Long은 diary의 pk의 자료형
    //List<Diary> findByMember_Id(Long id);

    List<Diary> findByMarker_Id(Long idx);
}
