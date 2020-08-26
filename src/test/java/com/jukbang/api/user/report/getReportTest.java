package com.jukbang.api.user.report;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.request.CreateReportRequest;
import com.jukbang.api.user.service.ReportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class getReportTest extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("신고 상세 조회하기(성공)")
    void getReportSuccess() throws Exception {
        Long roomId = roomFactory.generateRoom("Seller1");
        Long reportId = reportFactory.generateReport(1);
        mockMvc.perform(RestDocumentationRequestBuilders.get("/admin/report/{univId}/{roomId}/{reportId}", 1,roomId,reportId))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("getReport"))
        ;
    }
}
