package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.service.CommunityService;
import com.jukbang.api.room.dto.ExtraOption;
import com.jukbang.api.room.dto.Facilities;
import com.jukbang.api.room.dto.Location;
import com.jukbang.api.room.dto.RoomInformation;
import com.jukbang.api.room.request.CreateReviewRequest;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.service.ReviewService;
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

public class CreateReview extends BaseControllerTest {
    @Autowired
    private ReviewService reviewService;
    private RoomService roomService;
    @Test
    @WithMockUser("TestUser1")
    @DisplayName("방 리뷰 생성하기 (성공)")
    void createReviewSuccess() throws Exception{
        CreateRoomRequest createRoomRequest = CreateRoomRequest.builder()
                .Univid(1)
                .pictureCount(1)
                .roomInformation(new RoomInformation())
                .extraOption(new ExtraOption())
                .description("good")
                .location(new Location())
                .facilities(new Facilities())
                .build();

        long roomid = roomService.createRoom("seller",createRoomRequest);

        CreateReviewRequest createReviewRequest = CreateReviewRequest.builder()
                .id(1)
                .writer("writer")
                .body("good")
                .roomid( (int)roomid)
                .univid(1)
                .score(10)
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/review/{univId}/{roomId}", 1, roomid)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(createReviewRequest))
        )

                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("CreateReview"))
        ;
    }
}
