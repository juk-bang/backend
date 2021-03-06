package com.jukbang.api.user.profile;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.service.PostService;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class getMyPostsTest extends BaseControllerTest {

    @Autowired
    private PostService postService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("내가 쓴 게시글들 조회하기(성공)")
    void getMyPostsSuccess() throws Exception {

        userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();

        postFactoryAll.generatePost(1,"TestUser1");
        postFactoryAll.generatePost(1,"TestUser1");

        mockMvc.perform(RestDocumentationRequestBuilders.get("/userinfo/posts/{userId}", "TestUser1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("getMyPosts"))
        ;
    }
}

