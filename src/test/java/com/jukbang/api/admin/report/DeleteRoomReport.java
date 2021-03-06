package com.jukbang.api.admin.report;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.request.RoomReportRequest;
import com.jukbang.api.room.service.RoomReportService;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteRoomReport extends BaseControllerTest {
    @Autowired
    RoomReportService roomReportService ;

    @Test
    @DisplayName("신고한 방 삭제하기 (성공)")
    void deleteReportRoomSuccess() throws Exception{
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_ADMIN).getAccessToken();
        userFactory.signUpUser(2, UserRole.ROLE_LANDLORD);
        userFactory.signUpUser(3, UserRole.ROLE_STUDENT);

        Long roomId = roomFactory.generateRoom("TestUser2");

        RoomReportRequest roomReportRequest = RoomReportRequest.builder()
                .detail("상당히 불쾌합니다.")
                .type(1)
                .build();

        Long roomReportId = roomReportService.reportRoom(roomId,roomReportRequest);


        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/admin/report/rooms/{roomid}/{reportid}",roomId,roomReportId)
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("deleteRoomReport"))
        ;

    }
}
