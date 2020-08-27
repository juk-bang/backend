package com.jukbang.api.community;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.entity.Community;
import com.jukbang.api.community.request.CreateCommentRequest;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.service.CommentsService;
import com.jukbang.api.community.service.CommunityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetCommentsList extends BaseControllerTest {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommunityService communityService;

    @Test
    @DisplayName("게시글 별 전체 댓글 리스트 불러오기(성공)")
    void GetCommentsListSuccess() throws Exception {
        CreatePostRequest createPostRequest = CreatePostRequest.builder()
                .writer("TestUser1")
                .title("게시글 제목")
                .body("게시글 본문")
                .id(1)
                .build();

        Long postId = communityService.SavePost(1,createPostRequest);
        CreateCommentRequest createCommentRequest = CreateCommentRequest.builder()
                .id(1)
                .writer("writer1")
                .body("body body")
                .build();

        commentsService.SaveComment(1, Math.toIntExact(postId), createCommentRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/community/comments/{univId}/{postId}", 1,postId))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetCommentsList"))
        ;
    }
}
