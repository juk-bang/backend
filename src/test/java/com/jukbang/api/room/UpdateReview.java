package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.service.CommunityService;
import com.jukbang.api.room.dto.ExtraOption;
import com.jukbang.api.room.dto.Facilities;
import com.jukbang.api.room.dto.Location;
import com.jukbang.api.room.dto.RoomInformation;
import com.jukbang.api.room.request.CreateReviewRequest;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.request.UpdateReviewRequest;
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

public class UpdateReview extends BaseControllerTest {
    @Autowired
    private ReviewService reviewService;
    private RoomService roomService;
    @Test
    @WithMockUser("TestUser1")
    @DisplayName("리뷰 수정하기(성공)")
    void updateReviewSuccess() throws Exception{

        CreateRoomRequest createRoomRequest = CreateRoomRequest.builder()
                .Univid(1)
                .pictureCount(1)
                .roomInformation(new RoomInformation())
                .extraOption(new ExtraOption())
                .description("good")
                .location(new Location())
                .facilities(new Facilities())
                .build();

        roomService.createRoom("seller",createRoomRequest);


        CreateReviewRequest createReviewRequest = CreateReviewRequest.builder()
                .id(1)
                .writer("writer")
                .body("good")
                .score(10)
                .build();

        reviewService.SaveReview(1,1,createReviewRequest);

        UpdateReviewRequest updateReviewRequest = UpdateReviewRequest.builder()
                .body("good good")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/review/{univId}/{roomId}/{reviewId}", 1, 1,"userId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(updateReviewRequest))
        )

                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("UpdateReview"))
        ;
    }
}
