package com.example.mapmory.member.service;

import com.example.mapmory.member.domain.Member;
import com.example.mapmory.member.repository.MemberRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class UserInfoService {
    private static final String requestURL = "https://kapi.kakao.com/v2/user/me";
    private static final HashMap<String, Object> userInfo = new HashMap<String, Object>();

    @Autowired
    MemberRepository memberRepository;


    public UserInfoService() throws IOException {
    }

    public int setHttpURLConnection(String accessToken) throws IOException {
        URL url = new URL(requestURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestProperty("Authorization", "Bearer " + accessToken);//헤더값
        httpURLConnection.setRequestMethod("GET");

        return httpURLConnection.getResponseCode();
    }


    public Member getUserId(String accessToken) throws IOException {
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        URL url = new URL(reqURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        int responseCode = conn.getResponseCode();
        System.out.println("responseCode : " + responseCode);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";
        String result = "";
        while ((line = br.readLine()) != null) {
            result += line;
        }
        JsonElement element = JsonParser.parseString(result);

        JsonObject kakao_account = element.getAsJsonObject();
        String id = kakao_account.getAsJsonObject().get("id").getAsString();

        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
        System.out.println("닉네임은" + nickname);
        System.out.println("아이디는" + id);
        return new Member(nickname, id);
    }


    public void saveMember(String accessToken) throws IOException {
        getUserId(accessToken);

        memberRepository.save(getUserId(accessToken));
    }

}

