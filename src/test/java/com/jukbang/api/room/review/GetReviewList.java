package com.jukbang.api.room.review;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.request.CreateReviewRequest;
import com.jukbang.api.room.service.ReviewService;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetReviewList extends BaseControllerTest {
    @Autowired
    private ReviewService reviewService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("리뷰 리스트 불러오기 (성공)")
    void getReviewListSuccess() throws Exception{
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();
        userFactory.signUpUser(99, UserRole.ROLE_LANDLORD);
        Long roomId = roomFactory.generateRoom("TestUser99");

        CreateReviewRequest createReviewRequest = new CreateReviewRequest("test", 10);
        Long reviewId = reviewService.SaveReview(roomId, "TestUser1", createReviewRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/review/{roomId}", roomId)
            .header("Authorization", "Bearer " + accessToken)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetReviewList"))
        ;
    }
}
