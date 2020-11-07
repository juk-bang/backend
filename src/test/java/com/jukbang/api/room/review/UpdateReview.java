package com.jukbang.api.room.review;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.request.CreateReviewRequest;
import com.jukbang.api.room.request.UpdateReviewRequest;
import com.jukbang.api.room.service.ReviewService;
import com.jukbang.api.user.UserRole;
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

public class UpdateReview extends BaseControllerTest {
    @Autowired
    private ReviewService reviewService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("리뷰 수정하기(성공)")
    void updateReviewSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();
        userFactory.signUpUser(99, UserRole.ROLE_LANDLORD);
        Long roomId = roomFactory.generateRoom("TestUser99");

        CreateReviewRequest createReviewRequest = new CreateReviewRequest("test", 10);
        Long reviewId = reviewService.SaveReview(roomId, "TestUser1", createReviewRequest);
        UpdateReviewRequest updateReviewRequest = new UpdateReviewRequest("testChanged",2);

        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/review/{reviewId}", reviewId)
                .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + accessToken)
            .content(this.objectMapper.writeValueAsString(updateReviewRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("UpdateReview"))
        ;
    }
}
