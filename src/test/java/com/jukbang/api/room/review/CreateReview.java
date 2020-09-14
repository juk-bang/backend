package com.jukbang.api.room.review;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.request.CreateReviewRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class CreateReview extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("방 리뷰 생성하기 (성공)")
    void createReviewSuccess() throws Exception {

        long roomId = roomFactory.generateRoom("SellerId");

        CreateReviewRequest createReviewRequest = CreateReviewRequest.builder()
                .id(1)
                .writer("writer")
                .body("good")
                .title("제목")
                .roomid((int) roomId)
                .univid(1)
                .score(10)
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/review/{univId}/{roomId}", 1, roomId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(createReviewRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("CreateReview"))
        ;
    }
}
