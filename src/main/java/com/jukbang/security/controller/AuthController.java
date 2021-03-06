package com.jukbang.security.controller;

import com.jukbang.security.request.RefreshRequest;
import com.jukbang.security.request.SignInRequest;
import com.jukbang.security.request.SignUpRequest;
import com.jukbang.security.response.RefreshResponse;
import com.jukbang.security.response.SignInResponse;
import com.jukbang.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 회원 인증 컨트롤러
 *
 * @author always0ne
 * @version 1.0
 */
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/auth", produces = MediaTypes.HAL_JSON_VALUE)
public class AuthController {

    private final AuthService authService;

    /**
     * 토큰 발급받기
     *
     * @param signInRequest 사용자 ID, 비밀번호
     * @return accessToken
     */
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse signIn(
            @RequestBody SignInRequest signInRequest
    ) {
        return this.authService.signIn(signInRequest.getId(), signInRequest.getPassword());
    }

    /**
     * 회원 가입하기
     *
     * @param signUpRequest 사용자 ID, 비밀번호, 사용자 유형
     * @return accessToken
     */
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse signUp(
            @RequestBody SignUpRequest signUpRequest
    ) {
        return this.authService.signUp(signUpRequest);
    }

    /**
     * 아이디 중복 체크하기
     *
     * @param userId 중복확인할  ID
     * @return 사용가능 여부
     */
    @GetMapping("/checkid/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String idCheck(
            @PathVariable String userId
    ) {
        this.authService.idCheck(userId);
        return "사용가능한 아이디입니다.";
    }

    /**
     * RefreshToken 으로 AccessToken 재발급 받기
     *
     * @param refreshRequest 토큰 갱신 요청
     * @return AccessToken
     */
    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public RefreshResponse getNewAccessToken(
            @RequestBody RefreshRequest refreshRequest
    ) {
        return this.authService.refreshAccessToken(refreshRequest);
    }

}
