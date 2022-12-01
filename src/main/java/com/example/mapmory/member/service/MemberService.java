package com.example.mapmory.member.service;

import com.example.mapmory.member.domain.Role;
import com.example.mapmory.member.domain.entity.Member;
import com.example.mapmory.member.dto.MemberDto;
import com.example.mapmory.member.dto.MemberRequestDto;
import com.example.mapmory.member.dto.MemberResponseDto;
import com.example.mapmory.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    public Member joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        //memberDto.setPassword로 암호화된 비밀번호를 저장함.
        //memberRequestDto에 요청된 비밀번호가 들어오면,
        //memberDto의 암호화를 복호화하여 복호화한 값과 비밀번호를 비교한다.
        System.out.println("비밀번호 는"+ memberDto.getPassword());
        if(memberRepository.existsByEmail(memberDto.getEmail()) == true){
            return null;
        }
        return memberRepository.save(memberDto.toEntity());
    }

    public MemberResponseDto findUser(MemberRequestDto memberRequestDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member member = memberRepository.findByEmail(memberRequestDto.getEmail()).get();
        System.out.println(member.getPassword());
        if(passwordEncoder.matches(memberRequestDto.getPassword(),member.getPassword())) {
            return new MemberResponseDto(member);
        }
       return null;
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

    public MemberResponseDto findBy(final MemberRequestDto params){
        MemberResponseDto entity = memberRepository.findByEmailAndPassword(params.getEmail(), params.getPassword());
        return entity;
    }

    public Member findByNickname(final Long id ){

        return memberRepository.findById(id).get();
    }
}