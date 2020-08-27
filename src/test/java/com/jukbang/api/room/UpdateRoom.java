package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.service.CommunityService;
import com.jukbang.api.room.dto.*;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.request.UpdateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateRoom extends BaseControllerTest {
    @Autowired
    private RoomService roomService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("방 수정하기 (성공)")
    void updateRoomSuccess() throws Exception{
        CreateRoomRequest createRoomRequest = CreateRoomRequest.builder()
                .Univid(1)
                .pictureCount(1)
                .roomInformation(new RoomInformation("hi",1,1,1,new Price(1,1,1)))
                .extraOption(new ExtraOption(true,true,true,true,true,true,true,true) )
                .description("good")
                .location(new Location(1.0 , 2.0))
                .facilities(new Facilities(true,true))
                .build();

        long roomid =  roomService.createRoom("seller",createRoomRequest);

        UpdateRoomRequest udpateRoomRequest = UpdateRoomRequest.builder()
                .description("updated")
                .build();


        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/manager/manageroom/{sellerId}/{roomId}","sellerId",roomid)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(udpateRoomRequest))
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("UpdateRoom"))
        ;
    }
}
