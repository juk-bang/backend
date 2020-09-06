package com.jukbang.api.room;

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

@DisplayName("집주인 방 리스트 가져오기")
public class GetRoomsForLandlord extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("집주인 방 리스트 가져오기 (성공)")
    void getSellerRoomListSuccess() throws Exception{
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();
        roomFactory.generateRoom("TestUser" + 1);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/landlord/rooms")
        .header("Authorization", "Bearer "+accessToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetSellerRoomList"))
        ;
    }

}
