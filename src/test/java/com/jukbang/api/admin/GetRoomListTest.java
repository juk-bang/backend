package com.jukbang.api.admin;

import com.jukbang.api.common.BaseControllerTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class GetRoomListTest extends BaseControllerTest {

    @Test
    @DisplayName("승인받아야 할 방 리스트 가져오기(성공)")
    void GetRoomSuccess() throws Exception {
        roomFactory.generateRoom("Seller1");

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/admin/permissionroom/{univId}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("shouldPermitRooms"))
        ;
    }

}
