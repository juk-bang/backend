package com.jukbang.api.factory;

import com.jukbang.api.security.request.SignUpRequest;
import com.jukbang.api.security.response.SignInResponse;
import com.jukbang.api.security.service.AuthService;
import com.jukbang.api.user.UserRole;
import com.jukbang.api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserFactory {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public SignInResponse signUpUser(int i) {
        return authService.signUp(generateSignUpRequest(i, UserRole.ROLE_STUDENT));
    }

    @Transactional
    public Long generateUser(int i) {
        authService.signUp(generateSignUpRequest(i, UserRole.ROLE_STUDENT));
        return userRepository.findByUserId("TestUser" + i).get().getAccountId();
    }

    public SignUpRequest generateSignUpRequest(int index, UserRole role) {
        return SignUpRequest.builder()
                .id("TestUser" + index)
                .password("password")
                .univId(1)
                .role(role)
                .build();
    }
}
