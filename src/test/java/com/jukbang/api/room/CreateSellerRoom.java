package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.dto.*;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class CreateSellerRoom extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("판매자 방 생성하기 (성공)")
    void createSellerRoomSuccess() throws Exception {
        CreateRoomRequest createRoomRequest = CreateRoomRequest.builder()
                .univId(1)
                .pictureCount(0)
                .roomInformation(new RoomInformation("addr",1,2,2,new Price(1,1,1)))
                .extraOption(new ExtraOption(true,true,true,true,true,true,true,true))
                .description("good")
                .location(new Location(1, 1))
                .facilities(new Facilities(true, true))
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/manager/manageroom/{sellerId}", "seller1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(createRoomRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("CreateSellerRoom"))
        ;


    }
}
