package com.jukbang.api.factory;

import com.jukbang.api.security.response.SignInResponse;
import com.jukbang.api.security.service.AuthService;
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
        return authService.signUp(
                "TestUser" + i,
                "password",
                1
        );
    }

    @Transactional
    public Long generateUser(int i) {
        authService.signUp("TestUser" + i, "password", 1);
        return userRepository.findByUserId("TestUser"+i).get().getId();
    }
}
