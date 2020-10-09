package com.jukbang.api.community;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.CreateCommentRequest;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.service.CommentsService;
import com.jukbang.api.community.service.PostService;
import com.jukbang.api.user.UserRole;
import com.jukbang.api.user.entity.User;
import com.jukbang.api.user.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteComment extends BaseControllerTest {

    @Test
    @DisplayName("게시글 삭제하기 (성공)")
    void DeleteCommentSuccess() throws Exception {

        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();

        Long postId = postFactory.generatePost(1,"TestUser1");

        commentFactory.generateComment(postId,"TestUser1");

        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/community/{univId}/{postId}/comments/{commentId}",
                1,postId,1)
                .header("Authorization", "Bearer " + accessToken))

                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("DeleteComment"))
        ;
    }
}

