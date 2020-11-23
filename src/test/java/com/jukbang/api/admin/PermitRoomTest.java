package com.jukbang.api.admin;

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

public class PermitRoomTest extends BaseControllerTest {

    @Test
    @DisplayName("방 승인하기(성공)")
    void permitRoomSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(2, UserRole.ROLE_ADMIN).getAccessToken();
        userFactory.signUpUser(1, UserRole.ROLE_LANDLORD);

        Long roomId = roomFactory.generateRoom("TestUser1");
        mockMvc.perform(RestDocumentationRequestBuilders.post("/admin/permission/{univId}/{roomId}", 1, roomId)
            .header("Authorization", "Bearer " + accessToken))
            .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("permitRoom"))
        ;
    }

}
