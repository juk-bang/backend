package com.jukbang.api.community;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.CreateCommentRequest;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.service.CommentsService;
import com.jukbang.api.community.service.PostService;
import com.jukbang.api.user.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class GetCommentsList extends BaseControllerTest {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private PostService postService;

    @Test
    @DisplayName("게시글 별 전체 댓글 리스트 불러오기(성공)")
    void GetCommentsListSuccess() throws Exception {

        Long postId = postFactory.generatePost(1,"TestUser");

        CreateCommentRequest createCommentRequest = CreateCommentRequest.builder()
                .writer(new User())
                .body("TestBody")
                .build();

        commentsService.saveComment(postId,"TestUser", createCommentRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/community/{univId}/{postId}/comments", 1,postId))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetCommentsList"))
        ;
    }
}
