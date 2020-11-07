package com.jukbang.api.user.profile;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import com.jukbang.api.user.repository.UserRepository;
import com.jukbang.api.user.request.UpdateUserRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

public class updateUserTest extends BaseControllerTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  @WithMockUser("TestUser1")
  @DisplayName("유저정보 수정하기(성공)")
  void updateUserSuccess() throws Exception {
    String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();
    UpdateUserRequest updateUserRequest = new UpdateUserRequest("newPassword");
    Long id = userRepository.findByUserId("TestUser1").get().getAccountId();
    mockMvc.perform(RestDocumentationRequestBuilders.put("/userinfo/{id}", id)
        .contentType(MediaType.APPLICATION_JSON)
        .header("Authorization", "Bearer " + accessToken)
        .content(this.objectMapper.writeValueAsString(updateUserRequest)))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(document("updateUser"))
    ;
  }
}
