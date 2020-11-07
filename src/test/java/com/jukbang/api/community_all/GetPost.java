package com.jukbang.api.community_all;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetPost extends BaseControllerTest {


    @Test
    @DisplayName("커뮤니티(전체)_각 게시글 불러오기(성공)")
    void GetPostListSuccess() throws Exception {
        userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();

        Long postId = postFactoryAll.generatePost(1,"TestUser1");

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/community/{role}/{univId}/{postId}","all",1,postId))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetPost"))
        ;
    }





}
