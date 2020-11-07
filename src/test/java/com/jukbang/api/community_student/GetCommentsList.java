package com.jukbang.api.community_student;

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
    @DisplayName("커뮤니티(학생)_게시글 별 전체 댓글 리스트 불러오기(성공)")
    void GetCommentsListSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();

        Long postId = postFactoryStudent.generatePost(1,"TestUser1");
        System.out.println("****************************** create post success **********************************");
        System.out.println("POST : " + postId);

        Long comment1 = commentFactory.generateComment(postId,"TestUser1");
        System.out.println("****************************** create comment 1 success **********************************");
        System.out.println("POST : " + postId +"  COMMENT : " + comment1);

        Long comment2 = commentFactory.generateComment(postId,"TestUser1");
        System.out.println("****************************** create comment 2 success **********************************");
        System.out.println("POST : " + postId);
        System.out.println("POST : " + postId +"  COMMENT : " + comment2);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/community/{role}/{univId}/{postId}/comments", "student",1,postId)
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetCommentsList"))
        ;
    }
}
