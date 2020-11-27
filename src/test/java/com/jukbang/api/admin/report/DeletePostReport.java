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

public class DeletePostReport extends BaseControllerTest {
    @Autowired
    PostReportService postReportService ;

    @Test
    @DisplayName("신고한 커뮤니티 게시글 삭제하기 (성공)")
    void deleteReportPostSuccess() throws Exception{
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_ADMIN).getAccessToken();
        userFactory.signUpUser(2, UserRole.ROLE_LANDLORD);
        userFactory.signUpUser(3, UserRole.ROLE_STUDENT);

        Long postId = postFactoryAll.generatePost(1,"TestUser2");

        PostReportRequest postReportRequest = PostReportRequest.builder()
                .detail("상당히 불쾌합니다.")
                .type(1)
                .build();

        Long postReportId = postReportService.reportPost(postId,1, CommunityRole.all,postReportRequest);


        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/admin/report/community/{postid}/{reportid}",postId,postReportId)
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("deletePostReport"))
        ;}
}
