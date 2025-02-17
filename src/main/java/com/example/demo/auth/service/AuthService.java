package com.example.demo.auth.service;

import com.example.demo.user.entity.User;
import com.example.demo.auth.JwtTokenProvider;
import com.example.demo.auth.dto.KakaoUserInfoResponseDto;
import com.example.demo.auth.dto.LoginResponseDto;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponseDto login(KakaoUserInfoResponseDto userInfoResponseDto) {
        if (userRepository.findByEmail(userInfoResponseDto.getKakaoAccount().email).isPresent()) {
            User user = userRepository.findByEmail(userInfoResponseDto.getKakaoAccount().email).get();
            String token = jwtTokenProvider.createToken(user.getId());

            // 디버깅: 토큰이 생성되지 않았다면 로그 출력
            if (token == null || token.isEmpty()) {
                throw new IllegalStateException("JWT Token generation failed");
            }

            LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                    .memberId(user.getId())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .token(token)
                    .build();
            return loginResponseDto;
        }
        else {
            User user = User.builder()
                    .username(userInfoResponseDto.kakaoAccount.profile.nickName)
                    .email(userInfoResponseDto.kakaoAccount.email)
                    .profileImage(userInfoResponseDto.kakaoAccount.profile.profileImageUrl)
                    .password("1234")
                    .build();

            userRepository.save(user);

            String token = jwtTokenProvider.createToken(user.getId());

            // 디버깅: 토큰이 생성되지 않았다면 로그 출력
            if (token == null || token.isEmpty()) {
                throw new IllegalStateException("JWT Token generation failed");
            }

            LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                    .memberId(user.getId())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .token(token)
                    .build();
            return loginResponseDto;
        }
    }

}
