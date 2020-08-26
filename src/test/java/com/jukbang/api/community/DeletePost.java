package com.jukbang.api.community;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.service.CommunityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeletePost extends BaseControllerTest {

    @Autowired
    private CommunityService communityService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("게시글 삭제하기 (성공)")
    void DeletePostSuccess() throws Exception{
        CreatePostRequest createPostRequest = CreatePostRequest.builder()
                .id(1)
                .title("post1")
                .writer("writer1")
                .body("body")
                .build();

        Long postId= communityService.SavePost(1,createPostRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/community/{univId}/{postid}",1,postId))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("DeletePost"))
        ;
    }


}
