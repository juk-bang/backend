package com.jukbang.api.user.profile;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import com.jukbang.api.user.request.UpdateUserRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class updateUserTest extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("유저정보 수정하기(성공)")
    void updateUserSuccess() throws Exception {
        userFactory.signUpUser(1, UserRole.ROLE_STUDENT);
        UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
                .id(1)
                .univid(1)
                .userId("testuser2")
                .build();
        mockMvc.perform(RestDocumentationRequestBuilders.put("/userinfo/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(updateUserRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("updateUser"))
        ;
    }
}
