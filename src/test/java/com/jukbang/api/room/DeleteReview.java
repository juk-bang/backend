package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.request.CreateReviewRequest;
import com.jukbang.api.room.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteReview extends BaseControllerTest {
    @Autowired
    private ReviewService reviewService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("리뷰 삭제하기(성공)")
    void deleteReviewSuccess() throws Exception {
        Long roomId = roomFactory.generateRoom("seller");

        CreateReviewRequest createReviewRequest = CreateReviewRequest.builder()
                .id(1)
                .writer("writer")
                .body("good")
                .score(10)
                .title("title")
                .build();

        Long reviewId = reviewService.SaveReview(1, Math.toIntExact(roomId), createReviewRequest);


        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/review/{univId}/{roomId}/{reviewId}", 1, roomId, reviewId))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("DeleteReview"))
        ;
    }
}
