package com.example.mapmory.member.controller;

import com.example.mapmory.member.service.TokenService;
import com.example.mapmory.member.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/mapmory")
public class OauthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value="/callbackKakao", method= RequestMethod.GET)
    public void enterKakaoLoginPage(@RequestParam(value = "code", required = false) String code) throws Exception {
        String access_Token = tokenService.getAccessToken(code);
        userInfoService.saveMember(access_Token);
    }


}