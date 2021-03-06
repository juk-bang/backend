package com.jukbang.api.community_all;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class GetCommentsList extends BaseControllerTest {
    @Test
    @DisplayName("커뮤니티(전체)_게시글 별 전체 댓글 리스트 불러오기(성공)")
    void GetCommentsListSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();
        userFactory.signUpUser(2, UserRole.ROLE_STUDENT).getAccessToken();

        Long postId = postFactoryAll.generatePost(1,"TestUser1");

        Long comment1 = commentFactory.generateComment(postId,"TestUser2");

        Long comment2 = commentFactory.generateComment(postId,"TestUser1");

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/community/{role}/{univId}/{postId}/comments", "all",1,postId)
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetCommentsList"))
        ;
    }
}
