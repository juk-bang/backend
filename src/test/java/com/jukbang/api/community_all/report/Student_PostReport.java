package com.jukbang.api.community_all.report;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.PostReportRequest;
import com.jukbang.api.community.service.PostReportService;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class Student_PostReport extends BaseControllerTest {
    @Autowired
    private PostReportService postReportService;

    @Test
    @DisplayName("학생커뮤니티_방신고하기 (성공)")
    void reportPostSuccess() throws Exception {
        userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();
        String accessToken = userFactory.signUpUser(2, UserRole.ROLE_STUDENT).getAccessToken();

        Long postId = postFactoryStudent.generatePost(1,"TestUser1");

        PostReportRequest postReportRequest = PostReportRequest.builder()
                .type(1)
                .detail("맘에 안들고 신경질이 납니다.")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/community/{role}/{univId}/{postId}/report","student",1,postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(postReportRequest))
                .header("Authorization", "Bearer " + accessToken)
        )

                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("reportPost_STUDENT"))
        ;
    }
}

