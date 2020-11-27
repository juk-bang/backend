package com.jukbang.api.user.profile;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import com.jukbang.api.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class deleteUserTest extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("유저 삭제하기(성공)")
    void deleteUserSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();

        mockMvc.perform(RestDocumentationRequestBuilders.delete("/userinfo")
            .header("Authorization", "Bearer " + accessToken)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("deleteUser"))
        ;
    }
}
