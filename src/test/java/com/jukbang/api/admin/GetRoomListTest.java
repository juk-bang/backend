package com.jukbang.api.admin;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.dto.*;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetRoomListTest extends BaseControllerTest {

    @Autowired
    private RoomService roomService;

    @Test
    @DisplayName("승인받아야 할 방 리스트 가져오기(성공)")
    void GetRoomSuccess() throws Exception {
        CreateRoomRequest createRoomRequest = CreateRoomRequest.builder()
                .description("설명")
                .extraOption(new ExtraOption(true, true, true, true, true, true, true, true))
                .facilities(new Facilities(true, true))
                .location(new Location(123, 412))
                .pictureCount(0)
                .roomInformation(new RoomInformation("주소", 1, 1, 1, new Price(1, 1, 1)))
                .Univid((long) 1)
                .build();

        roomService.createRoom("testId", createRoomRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/admin/permissionroom/{univId}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("shouldPermitRooms"))
        ;
    }

}
