package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.dto.*;
import com.jukbang.api.room.request.UpdateRoomRequest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                .header("Authorization", "Bearer "+accessToken)
                .content(this.objectMapper.writeValueAsString(updateRoomRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("updateRoom"))
        ;
    }
}
