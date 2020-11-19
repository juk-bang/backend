package com.jukbang.api.admin.report;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.CommunityRole;
import com.jukbang.api.community.request.PostReportRequest;
import com.jukbang.api.community.service.PostReportService;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetPostReport extends BaseControllerTest {
    @Autowired
    PostReportService postReportService;

    @Test
    @DisplayName("신고한 게시글 불러오기 (성공)")
    void getPostSuccess() throws Exception{
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_ADMIN).getAccessToken();
        userFactory.signUpUser(2, UserRole.ROLE_LANDLORD);
        userFactory.signUpUser(3, UserRole.ROLE_STUDENT);

        Long postId =postFactoryAll.generatePost(1,"TestUser1");
        PostReportRequest postReportRequest = PostReportRequest.builder()
                .detail("음란해요")
                .type(1)
                .build();
        Long postReportId = postReportService.reportPost(postId,1, CommunityRole.all,postReportRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/admin/report/community/{role}/{postId}/{postReportId}","all",postId,postReportId)
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetPostReport"))
        ;

    }

}
