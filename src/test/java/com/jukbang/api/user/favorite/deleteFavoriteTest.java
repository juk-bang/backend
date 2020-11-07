package com.jukbang.api.user.favorite;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import com.jukbang.api.user.service.FavoriteService;
import org.junit.jupiter.api.Disabled;
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
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();
        userFactory.signUpUser(99,UserRole.ROLE_LANDLORD);
        Long roomId = roomFactory.generateRoom("TestUser99");
        favoriteService.SaveFavorite(roomId,"TestUser1");
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/user/favorites/{roomId}", roomId)
            .header("Authorization", "Bearer " + accessToken)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("deleteFavorite"))
        ;
    }
}
