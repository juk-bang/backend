package com.jukbang.api.room.report;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.request.RoomReportRequest;
import com.jukbang.api.room.service.RoomReportService;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReportRoom extends BaseControllerTest {

    @Autowired
    private RoomReportService roomReportService;

    @Test
    @DisplayName("방신고하기 (성공)")
    void reportRoomSuccess() throws Exception {
        userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();
        String accessToken = userFactory.signUpUser(2, UserRole.ROLE_STUDENT).getAccessToken();

        Long roomId = roomFactory.generateRoom("TestUser1");

        RoomReportRequest roomReportRequest = RoomReportRequest.builder()
                .type(1)
                .detail("맘에 안들고 신경질이 납니다.")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/rooms/{roomid}/report",roomId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(roomReportRequest))
                .header("Authorization", "Bearer " + accessToken)
        )

                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("reportRoom"))
        ;
    }
}