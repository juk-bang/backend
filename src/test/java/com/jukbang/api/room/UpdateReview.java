package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.request.CreateReviewRequest;
import com.jukbang.api.room.request.UpdateReviewRequest;
import com.jukbang.api.room.service.ReviewService;
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

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("리뷰 수정하기(성공)")
    void updateReviewSuccess() throws Exception {
        Long roomId = roomFactory.generateRoom("seller");

        CreateReviewRequest createReviewRequest = CreateReviewRequest.builder()
                .id(1)
                .writer("writer")
                .body("good")
                .score(10)
                .title("title")
                .build();

        Long reviewId = reviewService.SaveReview(1, Math.toIntExact(roomId), createReviewRequest);

        UpdateReviewRequest updateReviewRequest = UpdateReviewRequest.builder()
                .body("good good")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/review/{univId}/{roomId}/{reviewId}", 1, roomId, reviewId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(updateReviewRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("UpdateReview"))
        ;
    }
}
