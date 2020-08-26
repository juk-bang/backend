package com.jukbang.api.community;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.request.UpdatePostRequest;
import com.jukbang.api.community.service.CommunityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdatePost  extends BaseControllerTest {
    @Autowired
    private CommunityService communityService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("게시글 수정하기 (성공)")
    void UpdatePostSuccess() throws Exception{
        CreatePostRequest createPostRequest = CreatePostRequest.builder()
                .id(1)
                .title("post1")
                .writer("writer1")
                .body("body body")
                .build();

        communityService.SavePost(1,createPostRequest);

        UpdatePostRequest updatePostRequest = UpdatePostRequest.builder()
                .id(1)
                .title("post2")
                .writer("writer1")
                .body("body * 2")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/community/{univId}/{postId}",1,1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(updatePostRequest))
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("UpdatePost"))
        ;
    }


}
