package com.jukbang.api.factory;

import com.jukbang.security.response.SignInResponse;
import com.jukbang.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserFactory {

    @Autowired
    private AuthService authService;

    @Transactional
    public SignInResponse generateUser(int i) {
         return authService.signUp(
                 "TestUser" + i,
                "password"
        );
    }
}
