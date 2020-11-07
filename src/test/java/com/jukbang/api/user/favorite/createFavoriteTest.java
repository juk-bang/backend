package com.jukbang.api.user.favorite;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class createFavoriteTest extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("방 찜하기(성공)")
    void createFavoriteSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();
        userFactory.signUpUser(99,UserRole.ROLE_LANDLORD);
        Long roomId = roomFactory.generateRoom("TestUser99");

        mockMvc.perform(RestDocumentationRequestBuilders.post("/user/favorites/{roomId}", roomId)
            .header("Authorization", "Bearer " + accessToken)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("createFavorite"))
        ;
    }
}
