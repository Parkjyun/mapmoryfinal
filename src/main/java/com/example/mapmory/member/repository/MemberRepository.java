package com.example.mapmory.member.repository;
import com.example.mapmory.member.domain.entity.Member;
import com.example.mapmory.member.dto.MemberResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);
    MemberResponseDto findByEmailAndPassword(final String email, final String password);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);



}
