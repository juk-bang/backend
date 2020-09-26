package com.jukbang.api.community;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.request.UpdatePostRequest;
import com.jukbang.api.community.service.PostService;
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

public class UpdatePost  extends BaseControllerTest {
    @Autowired
    private PostService postService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("게시글 수정하기 (성공)")
    void UpdatePostSuccess() throws Exception{
        userFactory.generateUser(1);

        Long postId = postFactory.generatePost(1,"TestUser");

        UpdatePostRequest updatePostRequest = UpdatePostRequest.builder()
                .title("TestTitle_2")
                .body("TestBody_2")
                .build();


        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/community/{univId}/{postId}",1,postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(updatePostRequest))
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("UpdatePost"))
        ;
    }


}
