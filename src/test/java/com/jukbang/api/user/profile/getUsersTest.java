package com.jukbang.api.user.profile;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class getUsersTest extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("유저 정보 조회하기(성공)")
    void getUserSuccess() throws Exception {
        userFactory.signUpUser(1, UserRole.ROLE_STUDENT);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/userinfo/{userId}", "TestUser1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("getUser"))
        ;
    }
}
