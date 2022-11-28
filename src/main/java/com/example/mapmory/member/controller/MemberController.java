package com.example.mapmory.member.controller;

import com.example.mapmory.member.dto.MemberDto;
import com.example.mapmory.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    // 회원가입 처리
    @PostMapping("/user/signup")
    public String execSignup(@RequestBody MemberDto memberDto) {
        System.out.println(memberDto.getEmail());
        System.out.println(memberDto.getNickname());
        memberService.joinUser(memberDto);
        return "post";
    }

    //닉네임이 중복인지 체크하기 위한 메서드
    @GetMapping("/check/nickname/{nickname}")
    public ResponseEntity<?> checkNickname(@PathVariable("nickname") String nickname) {

        if (memberService.checkEmail(nickname)) {
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    //이메일이 중복인지 체크하기 위한 메서드
    @GetMapping("check/email/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable("email") String email) {
        if (memberService.checkEmail(email)) {
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    //로그인 성공시 유저 상태 저장, check메서드 수행,!상태 저장!
    /*@GetMapping("{email}/check")
    public ResponseEntity<?> check(@PathVariable("email") String email) {
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not user");
        }
        MemberDto memberDto;
    }*/
}