package com.jukbang.security.service;

import com.jukbang.api.user.entity.embedded.Filter;
import com.jukbang.security.JwtTokenProvider;
import com.jukbang.security.exception.CantSignInException;
import com.jukbang.security.exception.IdAlreadyExistsException;
import com.jukbang.security.request.RefreshRequest;
import com.jukbang.security.request.SignUpRequest;
import com.jukbang.security.response.RefreshResponse;
import com.jukbang.security.response.SignInResponse;
import com.jukbang.api.user.entity.User;
import com.jukbang.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * 회원 인증 서비스
 *
 * @author always0ne
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * 인증토큰 발급받기
     * 새로 로그인 할 때마다 RefreshToken 이 갱신된다.
     *
     * @param id       사용자 ID
     * @param password 사용자 비밀번호
     * @return accessToken
     * @throws CantSignInException 회원가입이 되어있지 않거나 잠긴 계정입니다.
     */
    @Transactional
    public SignInResponse signIn(String id, String password) {
        User user = this.userRepository.findByUserId(id)
                .orElseThrow(() -> new CantSignInException(id));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new CantSignInException(id);
        user.updateRefreshToken(jwtTokenProvider.createRefreshToken(user.getUserId(), user.getRoles()));

        return SignInResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(user.getUserId(), user.getRoles()))
                .refreshToken(user.getRefreshToken())
                .build();
    }

    /**
     * 회원 가입 하기
     * 회원가입과 동시에 인증토큰 발급
     *
     * @param signUpRequest 회원가입 요청
     * @return accessToken
     */
    @Transactional
    public SignInResponse signUp(SignUpRequest signUpRequest) {
        User user = this.userRepository.save(User.builder()
                .userId(signUpRequest.getId())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(Collections.singletonList(signUpRequest.getRole()))
                .refreshToken(jwtTokenProvider.createRefreshToken(signUpRequest.getId(), Collections.singletonList(signUpRequest.getRole())))
                .univId(signUpRequest.getUnivId())
                .filter(new Filter())
                .build());

        return SignInResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(user.getUserId(), user.getRoles()))
                .refreshToken(user.getRefreshToken())
                .build();
    }

    /**
     * 중복 아이디 체크
     *
     * @param id 사용자 ID
     * @throws IdAlreadyExistsException 이미 사용중인 아이디입니다.
     */
    @Transactional(readOnly = true)
    public void idCheck(String id) {
        if (this.userRepository.findByUserId(id).isPresent())
            throw new IdAlreadyExistsException(id);
    }

    /**
     * RefreshToken 으로 AccessToken 재발급
     *
     * @param refreshRequest AccessToken, RefreshToken
     * @return AccessToken
     */
    @Transactional
    public RefreshResponse refreshAccessToken(RefreshRequest refreshRequest) {
        String refreshId = jwtTokenProvider.getUserId(jwtTokenProvider.getClaimsFromToken(refreshRequest.getRefreshToken()));
        User user = userRepository.findByUserIdAndRefreshToken(refreshId, refreshRequest.getRefreshToken())
                .orElseThrow(() -> new CantSignInException(refreshId));

        return RefreshResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(user.getUserId(), user.getRoles()))
                .build();
    }
}
