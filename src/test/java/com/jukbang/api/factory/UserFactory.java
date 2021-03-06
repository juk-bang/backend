package com.jukbang.api.factory;

import com.jukbang.security.request.SignUpRequest;
import com.jukbang.security.response.SignInResponse;
import com.jukbang.security.service.AuthService;
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
    public SignInResponse signUpUser(int i, UserRole role) {
        return authService.signUp(generateSignUpRequest(i, role));
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
