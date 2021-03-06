package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("방 상세정보 조회")
public class GetRoomDetail extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("방 세부사항 가져오기 (성공)")
    void getRoomSuccess() throws Exception {
        userFactory.signUpUser(1, UserRole.ROLE_LANDLORD);
        Long roomId = roomFactory.generateRoom("TestUser" + 1);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/rooms/{roomId}", roomId))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetRoomDetail"))
        ;
    }

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("방 세부사항 가져오기 (성공)")
    void getRoomFailBecauseNotFound() throws Exception {
        userFactory.signUpUser(1, UserRole.ROLE_LANDLORD);
        Long roomId = roomFactory.generateRoom("TestUser" + 1);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/rooms/{roomId}", roomId+1))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andDo(document("1001"))
        ;
    }
}
