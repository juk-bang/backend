package com.jukbang.api.community;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.service.PostService;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetPostList extends BaseControllerTest {

    @Test
    @DisplayName("전체게시글 리스트 불러오기(성공)")
    void GetPostListSuccess() throws Exception {
        userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();
        userFactory.signUpUser(2, UserRole.ROLE_STUDENT).getAccessToken();

        postFactory.generatePost(1,"TestUser1");
        postFactory.generatePost(1,"TestUser2");

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/community/{univId}",1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetPostList"))
                ;


    }



}
