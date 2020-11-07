package com.jukbang.api.room.review;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.request.CreateReviewRequest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

public class CreateReview extends BaseControllerTest {

  @Test
  @WithMockUser("TestUser1")
  @DisplayName("방 리뷰 생성하기 (성공)")
  void createReviewSuccess() throws Exception {
    String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();
    userFactory.signUpUser(99, UserRole.ROLE_LANDLORD);
    Long roomId = roomFactory.generateRoom("TestUser99");

    CreateReviewRequest createReviewRequest = new CreateReviewRequest("test", 10);

    this.mockMvc.perform(RestDocumentationRequestBuilders.post("/review/{roomId}", roomId)
        .header("Authorization", "Bearer " + accessToken)
        .contentType(MediaType.APPLICATION_JSON)
        .content(this.objectMapper.writeValueAsString(createReviewRequest)))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(document("CreateReview"))
    ;
  }
}
