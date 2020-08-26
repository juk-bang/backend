package com.jukbang.api.user.report;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.request.CreateReportRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class createReportTest extends BaseControllerTest {

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("신고 작성하기(성공)")
    void createReportSuccess() throws Exception {
        Long roomId = roomFactory.generateRoom("Seller1");
        CreateReportRequest createReportRequest = reportFactory.generateReportRequest(1);
        mockMvc.perform(RestDocumentationRequestBuilders.post("/report/{univId}/{roomId}/{userId}", 1, roomId, "TestUser1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(createReportRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("createReport"))
        ;
    }
}
