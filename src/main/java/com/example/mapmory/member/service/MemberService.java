package com.example.mapmory.member.service;

import com.example.mapmory.member.domain.Role;
import com.example.mapmory.member.domain.entity.Member;
import com.example.mapmory.member.dto.MemberDto;
import com.example.mapmory.member.dto.MemberRequestDto;
import com.example.mapmory.member.dto.MemberResponseDto;
import com.example.mapmory.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;

    @Transactional
    public Long joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        if(memberRepository.existsByEmail(memberDto.getEmail()) == true){
            return null;
        }
        return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> userEntityWrapper = memberRepository.findByEmail(email);
        Member userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(email) ) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    public boolean checkEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    public boolean checkNickname(String nickname){
        return memberRepository.existsByNickname(nickname);
    }

/*    public MemberDto getUser(String email) {
        Optional<Member> memberEntity = memberRepository.findByEmail(email);
        if (memberEntity == null) throw new UsernameNotFoundException(email);


        return
    }*/
    public MemberResponseDto findBy(final MemberRequestDto params){
        MemberResponseDto entity = memberRepository.findByEmailAndPassword(params.getEmail(), params.getPassword());
        return entity;
    }
}