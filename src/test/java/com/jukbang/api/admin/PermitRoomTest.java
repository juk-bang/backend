package com.jukbang.api.admin;

import com.jukbang.api.common.BaseControllerTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class PermitRoomTest extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("방 승인하기(성공)")
    void permitRoomSuccess() throws Exception {
        Long roomId = roomFactory.generateRoom("seller1");
        mockMvc.perform(RestDocumentationRequestBuilders.post("/admin/permissionroom/{univId}/{roomId}", 1, roomId))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("permitRoom"))
        ;
    }

}
