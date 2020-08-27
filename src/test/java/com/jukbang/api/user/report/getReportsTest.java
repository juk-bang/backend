package com.jukbang.api.user.report;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.request.CreateReportRequest;
import com.jukbang.api.user.service.ReportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class getReportsTest extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("신고 조회하기(성공)")
    void getReportsSuccess() throws Exception {
        reportFactory.generateReport(1);
        mockMvc.perform(RestDocumentationRequestBuilders.get("/admin/report/{univId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("getReports"))
        ;
    }
}
