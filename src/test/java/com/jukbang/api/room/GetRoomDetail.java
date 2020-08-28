package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.dto.ExtraOption;
import com.jukbang.api.room.dto.Facilities;
import com.jukbang.api.room.dto.Location;
import com.jukbang.api.room.dto.RoomInformation;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class GetRoomDetail extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("방 세부사항 가져오기 (성공)")
    void getRoomListSuccess() throws Exception{
        Long roomId = roomFactory.generateRoom("sellerId");

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/roomdetail/{univId}/{roomId}", 1,roomId))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetRoomDetail"))
        ;
    }
}
