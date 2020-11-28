package com.jukbang.api.room.filter;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.room.repository.RoomRepository;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

@DisplayName("내 필터 가져오기")
public class GetFilter extends BaseControllerTest {

  @Autowired
  private RoomRepository roomRepository;

  @Test
  @WithMockUser("TestUser1")
  @DisplayName("내 필터 가져오기 (성공)")
  void getMyFilterSuccess() throws Exception {
    userFactory.signUpUser(1, UserRole.ROLE_LANDLORD);
    userFactory.signUpUser(2, UserRole.ROLE_LANDLORD);
    Long roomId1 = roomFactory.generateRoom("TestUser" + 1, 1000.0, 50.0);
    Long roomId2 = roomFactory.generateRoom("TestUser" + 2, 1000.0, 30.0);
    Long roomId3 = roomFactory.generateRoom("TestUser" + 2, 2000.0, 40.0);


    roomRepository.findById(roomId1).get().permitRoom();
    roomRepository.findById(roomId2).get().permitRoom();
    roomRepository.findById(roomId3).get().permitRoom();

    this.mockMvc.perform(RestDocumentationRequestBuilders.get("/rooms")
        .param("univId", String.valueOf(1))
        .param("monthlyLease", "30-50")
        .param("deposit", "0-1500"))
        .andExpect(status().isOk())
        .andDo(print())
    ;


      this.mockMvc.perform(RestDocumentationRequestBuilders.get("/user/filter"))
          .andExpect(status().isOk())
          .andDo(print())
          .andDo(document("getMyFilter"))
      ;
  }

}
