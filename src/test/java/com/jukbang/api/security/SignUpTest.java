package com.jukbang.api.security;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.security.request.SignUpRequest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("회원가입 테스트")
class SignUpTest extends BaseControllerTest {

    @Test
    @DisplayName("회원 가입하기(성공)")
    void signUpSuccess() throws Exception {
        SignUpRequest signUpRequest = userFactory.generateSignUpRequest(1,UserRole.ROLE_STUDENT);

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("signup"));
    }

    @Test
    @DisplayName("아이디 중복 조회하기(사용 가능할 때)")
    void idCheckSuccess() throws Exception {
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/auth/checkid/{userId}", "TestUser1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("idcheck"));
    }

    @Test
    @DisplayName("아이디 중복 조회하기(사용 불가능할 때)")
    void idCheckFailBecauseExists() throws Exception {
        this.userFactory.signUpUser(1, UserRole.ROLE_STUDENT);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/auth/checkid/{userId}", "TestUser1"))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andDo(document("0001"));
    }
}