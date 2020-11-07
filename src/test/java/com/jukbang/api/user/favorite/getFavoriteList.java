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

public class getFavoriteList extends BaseControllerTest {

    @Autowired
    private FavoriteService favoriteService;

    @Test
    @DisplayName("내 찜 목록 조회하기(성공)")
    void getFavoriteListSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();
        userFactory.signUpUser(99,UserRole.ROLE_LANDLORD);
        Long roomId = roomFactory.generateRoom("TestUser99");
        favoriteService.SaveFavorite(roomId,"TestUser1");
        mockMvc.perform(RestDocumentationRequestBuilders.get("/user/favorites")
            .header("Authorization", "Bearer " + accessToken)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("getFavoriteList"))
        ;
    }
}
