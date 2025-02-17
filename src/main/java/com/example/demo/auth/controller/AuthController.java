package com.example.demo.auth.controller;

import com.example.demo.auth.dto.KakaoUserInfoResponseDto;
import com.example.demo.auth.dto.LoginResponseDto;
import com.example.demo.auth.service.AuthService;
import com.example.demo.auth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final KakaoService kakaoService;
    private final AuthService authService;

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code) throws IOException {
        String accessToken = kakaoService.getKakaoAccessToken(code);

        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        LoginResponseDto loginResponseDto = authService.login(userInfo);

        return ResponseEntity.ok(loginResponseDto);
    }

}
