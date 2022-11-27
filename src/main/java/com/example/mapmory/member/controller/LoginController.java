package com.example.mapmory.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("mapmory/kakaologin")
    public String enterKakaoLoginPage(){


        return "kakaologin";
    }

}
