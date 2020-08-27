package com.jukbang.api.user.favorite;

import com.jukbang.api.common.BaseControllerTest;
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
        Long roomId = roomFactory.generateRoom("Seller1");
        mockMvc.perform(RestDocumentationRequestBuilders.post("/userinfo/favorites/{univId}/{roomId}/{userId}", 1, roomId, "TestUser1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("createFavorite"))
        ;
    }
}
