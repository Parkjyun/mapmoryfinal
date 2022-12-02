package com.example.mapmory.member.controller;

import com.example.mapmory.member.domain.entity.Member;
import com.example.mapmory.member.dto.MemberDto;
import com.example.mapmory.member.dto.MemberRequestDto;
import com.example.mapmory.member.dto.MemberResponseDto;
import com.example.mapmory.member.dto.NicknameResponseDto;
import com.example.mapmory.member.service.MemberService;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    // 회원가입 처리 // 포스트매핑
    @PostMapping("/user/signup")
    public Member execSignup(@RequestBody MemberDto memberDto) {
        System.out.println(memberDto.getEmail());
        System.out.println(memberDto.getNickname());
        Member memberEntity = memberService.joinUser(memberDto);
        return memberEntity;
    }

    //닉네임이 중복인지 체크하기 위한 메서드
    @GetMapping("/check/nickname/{nickname}")
    public ResponseEntity<?> checkNicknameDuplication(@PathVariable("nickname") String nickname) {
        if (memberService.checkEmail(nickname)) {
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.badRequest().body(false);
    }

    //이메일이 중복인지 체크하기 위한 메서드
    @GetMapping("/check/email/{email}")
    public ResponseEntity<?> checkEmailDuplication(@PathVariable("email") String email) {
        if (memberService.checkEmail(email)) {
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.badRequest().body(false);
    }

    //로그인 화면 -> 유저 PK값을 반환해줌
    @PostMapping("/user/signin")
    public MemberResponseDto signIn(@RequestBody MemberRequestDto memberRequestDto) {
        System.out.println("포스트로 들어온 아이디는:"+memberRequestDto.getEmail()+"비밀번호는"+memberRequestDto.getPassword());
        MemberResponseDto responseDto = memberService.findUser(memberRequestDto);
        return responseDto;
    }

    /*
        if(memberRequestDto.getEmail() == responseDto.getEmail()){
          if(memberRequestDto.getPassword() == responseDto.getPassword()){
              return ResponseEntity.ok().body(responseDto);
          }
        }
        return ResponseEntity.badRequest().body(responseDto);

     */
    //ID와 닉네임 반환
    @GetMapping("member/{id}/nickname")
    public NicknameResponseDto findMemberNickname(@PathVariable("id") Long id) {
        Member member = memberService.findByNickname(id);
        NicknameResponseDto responseDto = new NicknameResponseDto(member);
        return responseDto;
    }


}