package com.jukbang.api.user.favorite;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.service.FavoriteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class deleteFavoriteTest extends BaseControllerTest {

    @Autowired
    private FavoriteService favoriteService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("찜 취소하기(성공)")
    void deleteFavoriteSuccess() throws Exception {
        Long roomId = roomFactory.generateRoom("Seller1");
        userFactory.signUpUser(1);
        favoriteService.SaveFavorite(1, Math.toIntExact(roomId),"TestUser1");
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/userinfo/favorites/{univId}/{roomId}/{userId}", 1, roomId, "TestUser1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("deleteFavorite"))
        ;
    }
}
