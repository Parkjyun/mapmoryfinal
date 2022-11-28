package com.example.mapmory.member.repository;
import com.example.mapmory.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findById(Long Id);
    Optional<Member> findByEmail(String email);


    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);


}
