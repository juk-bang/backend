package com.jukbang.api.admin;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetRoomListTest extends BaseControllerTest {

    @Test
    @DisplayName("승인받아야 할 방 리스트 가져오기(성공)")
    void GetRoomSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(2, UserRole.ROLE_ADMIN).getAccessToken();
        userFactory.signUpUser(1, UserRole.ROLE_LANDLORD);

        roomFactory.generateRoom("TestUser1");

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/admin/permission/rooms")
            .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("shouldPermitRooms"))
        ;
    }

}
