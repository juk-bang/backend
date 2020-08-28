package com.jukbang.api.user.profile;

import com.jukbang.api.common.BaseControllerTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class deleteUserTest extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("유저 삭제하기하기(성공)")
    void deleteUserSuccess() throws Exception {
        Long id = userFactory.generateUser(1);

        mockMvc.perform(RestDocumentationRequestBuilders.delete("/userinfo/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("deleteUser"))
        ;
    }
}
