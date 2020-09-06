package com.jukbang.api.community;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.CreateCommentRequest;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.service.CommentsService;
import com.jukbang.api.community.service.PostService;
import com.jukbang.api.factory.PostFactory;
import com.jukbang.api.user.entity.User;
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

public class CreateComment extends BaseControllerTest {

    @Autowired
    private CommentsService commentsService;

    @Autowired

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("댓글 생성하기 (성공)")
    void CreateCommentSuccess() throws Exception {

        Long postId = postFactory.generatePost(1,"TestUser");

        CreateCommentRequest createCommentRequest = CreateCommentRequest.builder()
                .writer(new User())
                .body("TestCommnets")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/community/comments/{univId}/{postId}/comments", 1, postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(createCommentRequest))
        )

                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("CreateComment"))
        ;
    }


}
