package com.jukbang.api.community_student;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community_student.request.UpdatePostRequest;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdatePost  extends BaseControllerTest {

    @Test
    @DisplayName("커뮤니티(학생)_게시글 수정하기 (성공)")
    void UpdatePostSuccess() throws Exception{
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();

        Long postId = postFactoryStudent.generatePost(1,"TestUser1");

        UpdatePostRequest updatePostRequest = UpdatePostRequest.builder()
                .title("TestTitle_2")
                .body("TestBody_2")
                .build();


        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/community/{role}/{univId}/{postId}","student",1,postId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .content(this.objectMapper.writeValueAsString(updatePostRequest))
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("UpdatePost"))
        ;
    }


}
