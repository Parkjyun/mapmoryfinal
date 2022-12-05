package com.example.mapmory.marker.repository;

import com.example.mapmory.diary.domain.Diary;
import com.example.mapmory.marker.domain.Marker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkerRepository extends JpaRepository<Marker, Long> {


    List<Marker> findByMember_Id(Long id);

    Optional<Marker> findById(Long Id);
}
