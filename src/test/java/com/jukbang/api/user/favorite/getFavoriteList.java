package com.jukbang.api.user.favorite;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import com.jukbang.api.user.service.FavoriteService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class getFavoriteList extends BaseControllerTest {

    @Autowired
    private FavoriteService favoriteService;

    @Test
    @DisplayName("내 찜 목록 조회하기(성공)")
    void getFavoriteListSuccess() throws Exception {
        userFactory.signUpUser(1, UserRole.ROLE_STUDENT);
        Long roomId = roomFactory.generateRoom("Seller1");
        favoriteService.SaveFavorite(1, Math.toIntExact(roomId),"TestUser1");
        mockMvc.perform(RestDocumentationRequestBuilders.get("/userinfo/favorites/{userId}", "TestUser1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("getFavoriteList"))
        ;
    }
}
