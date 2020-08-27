package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.service.CommunityService;
import com.jukbang.api.room.dto.*;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteRoom extends BaseControllerTest {
    @Autowired
    private RoomService roomService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("방 삭제하기 (성공)")
    void DeleteRoomSuccess() throws  Exception{
        long roomId =  roomFactory.generateRoom("seller");

        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/manager/manageroom/{sellerId}/{roomId}","seller",roomId))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("DeleteRoom"))
        ;

    }
}
