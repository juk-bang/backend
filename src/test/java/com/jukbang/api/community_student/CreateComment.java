package com.jukbang.api.community_student;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community_student.request.CreateCommentRequest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateComment extends BaseControllerTest {

    @Test
    @DisplayName("커뮤니티(학생)_댓글 생성하기 (성공)")
    void CreateCommentSuccess() throws Exception {

        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();

        Long postId = postFactoryStudent.generatePost(1,"TestUser1");

        CreateCommentRequest createCommentRequest = CreateCommentRequest.builder()
                .body("TestComments")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/community/{role}/{univId}/{postId}/comments", "student",1, postId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .content(this.objectMapper.writeValueAsString(createCommentRequest))
        )

                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("CreateComment"))
        ;
    }


}
