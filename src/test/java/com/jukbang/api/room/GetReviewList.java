package com.jukbang.api.room;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.request.CreateReviewRequest;
import com.jukbang.api.room.service.ReviewService;
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
public class GetReviewList extends BaseControllerTest {
    @Autowired
    private ReviewService reviewService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("리뷰 리스트 불러오기 (성공)")
    void getReviewListSuccess() throws Exception{

        roomFactory.generateRoom("sellerId");

        CreateReviewRequest createReviewRequest = CreateReviewRequest.builder()
                .id(1)
                .writer("writer")
                .body("good")
                .score(10)
                .title("제목")
                .build();

        reviewService.SaveReview(1,1,createReviewRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/review/{univId}/{roomId}", 1, 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetReviewList"))
        ;
    }
}
