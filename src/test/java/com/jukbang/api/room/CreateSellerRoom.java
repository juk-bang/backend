package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.service.CommunityService;
import com.jukbang.api.room.dto.ExtraOption;
import com.jukbang.api.room.dto.Facilities;
import com.jukbang.api.room.dto.Location;
import com.jukbang.api.room.dto.RoomInformation;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateSellerRoom extends BaseControllerTest {
    @Autowired
    private RoomService roomService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("판매자 방 생성하기 (성공)")
    void createSellerRoomSuccess() throws Exception{
        CreateRoomRequest createRoomRequest = CreateRoomRequest.builder()
                .Univid(1)
                .pictureCount(1)
                .roomInformation(new RoomInformation())
                .extraOption(new ExtraOption())
                .description("good")
                .location(new Location())
                .facilities(new Facilities())
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/manager/manageroom/{sellerId}", "seller")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(createRoomRequest))
        )

                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("CreateSellerRoom"))
        ;


    }
}
