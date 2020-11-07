package com.jukbang.api.community_all;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community_student.CommunityRole;
import com.jukbang.api.community_student.request.CreatePostRequest;
import com.jukbang.api.community_student.service.PostService;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreatePost extends BaseControllerTest {

    @Autowired
    private PostService postService;

    @Test
    @DisplayName("커뮤니티(전체)_게시글 생성하기 (성공)")
    void CreatePostSuccess() throws Exception {

        CreatePostRequest createPostRequest = CreatePostRequest.builder()
                .title("TestTitle")
                .body("TestBody")
                .build();

        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();

        postService.savePost(1,"TestUser1", CommunityRole.student,createPostRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/community/{role}/{univId}","all",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(createPostRequest))
                .header("Authorization", "Bearer " + accessToken)
        )

                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("createPost"))
        ;
    }
}
