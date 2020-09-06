package com.jukbang.api.community;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.service.PostService;
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

@Disabled
public class CreatePost extends BaseControllerTest {

    @Autowired
    private PostService postService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("게시글 생성하기 (성공)")
    void CreatePostSuccess() throws Exception {

        CreatePostRequest createPostRequest = CreatePostRequest.builder()
                .writer(new User())
                .title("TestTitle")
                .body("TestBody")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/community/{univId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(createPostRequest))
        )

                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("createPost"))
        ;
    }
}
