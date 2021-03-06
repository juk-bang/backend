package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("집주인 방 삭제하기")
public class DeleteRoom extends BaseControllerTest {

    @Test
    @DisplayName("방 삭제하기 (성공)")
    void DeleteRoomSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();
        Long roomId = roomFactory.generateRoom("TestUser" + 1);

        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/landlord/rooms/{roomId}", roomId)
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("DeleteRoom"))
        ;
    }

    @Test
    @DisplayName("방 삭제하기 내 방이 아닐 때(실패)")
    void DeleteRoomFailBecauseNotMyRoom() throws Exception {
        userFactory.signUpUser(1, UserRole.ROLE_LANDLORD);
        String accessToken = userFactory.signUpUser(2, UserRole.ROLE_LANDLORD).getAccessToken();
        Long roomId = roomFactory.generateRoom("TestUser" + 1);

        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/landlord/rooms/{roomId}", roomId)
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andDo(document("1002"))
        ;
    }
}
