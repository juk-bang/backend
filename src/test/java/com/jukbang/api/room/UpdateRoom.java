package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.request.UpdateRoomRequest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("집주인 방 수정하기")
public class UpdateRoom extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("방 수정하기 (성공)")
    void updateRoomSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();
        Long roomId = roomFactory.generateRoom("TestUser" + 1);

        UpdateRoomRequest updateRoomRequest = roomFactory.generateUpdateRoomRequest();

        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/landlord/rooms/{roomId}", roomId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .content(this.objectMapper.writeValueAsString(updateRoomRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("updateRoom"))
        ;
    }

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("방 수정하기 방이 없을 때 (실패)")
    void updateRoomFailBecauseNotFound() throws Exception {
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();
        Long roomId = roomFactory.generateRoom("TestUser" + 1);

        UpdateRoomRequest updateRoomRequest = roomFactory.generateUpdateRoomRequest();

        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/landlord/rooms/{roomId}", roomId+1)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .content(this.objectMapper.writeValueAsString(updateRoomRequest)))
                .andExpect(status().isNotFound())
                .andDo(print())
        ;
    }

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("방 수정하기 수정 권한이 없을 때 (실패)")
    void updateRoomFailBecauseNotMyRoom() throws Exception {
        userFactory.signUpUser(1, UserRole.ROLE_LANDLORD);
        String accessToken = userFactory.signUpUser(2, UserRole.ROLE_LANDLORD).getAccessToken();
        Long roomId = roomFactory.generateRoom("TestUser" + 1);

        UpdateRoomRequest updateRoomRequest = roomFactory.generateUpdateRoomRequest();

        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/landlord/rooms/{roomId}", roomId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .content(this.objectMapper.writeValueAsString(updateRoomRequest)))
                .andExpect(status().isForbidden())
                .andDo(print())
        ;
    }
}
