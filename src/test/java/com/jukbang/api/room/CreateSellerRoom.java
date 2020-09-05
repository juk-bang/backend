package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateSellerRoom extends BaseControllerTest {

    @Test
    @DisplayName("판매자 방 생성하기 (성공)")
    void createSellerRoomSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();
        CreateRoomRequest createRoomRequest = roomFactory.generateCreateRoomRequest();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/landlord")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .content(this.objectMapper.writeValueAsString(createRoomRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("CreateSellerRoom"))
        ;


    }
}
