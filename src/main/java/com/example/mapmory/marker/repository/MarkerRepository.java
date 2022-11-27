package com.example.mapmory.marker.repository;

import com.example.mapmory.marker.domain.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkerRepository extends JpaRepository<Marker, Long> {
}
