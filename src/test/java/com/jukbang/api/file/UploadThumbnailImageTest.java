package com.jukbang.api.file;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.user.UserRole;
import java.io.File;
import java.io.FileInputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DisplayName("썸네일 이미지 업로드 테스트")
class UploadThumbnailImageTest extends BaseControllerTest {

  @Test
  @WithMockUser("TestUser1")
  @DisplayName("썸네일 이미지 업로드(성공)")
  void uploadThumbnailImageSuccess() throws Exception {
    String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();
    Long roomId = roomFactory.generateRoom("TestUser1");

    File targetFile = new File("./files/thumbnailImg/test.jpg");
    MockMultipartFile image = new MockMultipartFile(
        "image", targetFile.getName(), "image/jpeg", new FileInputStream(targetFile));

    this.mockMvc.perform(
        RestDocumentationRequestBuilders.fileUpload("/rooms/{roomId}/images/{imageId}", roomId, 1)
            .file(image)
            .accept(MediaTypes.HAL_JSON)
            .header("Authorization", "Bearer " + accessToken)
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(print())
        .andDo(document("uploadThumbnailImage"))
    ;
    this.mockMvc.perform(
        RestDocumentationRequestBuilders.get("/rooms/{roomId}/images/{imageId}", roomId, 1))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @WithMockUser("TestUser1")
  @DisplayName("썸네일 이미지 업로드(실패)잘못된 이름")
  void uploadThumbnailImageFailBecauseBadName() throws Exception {
    String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();
    Long roomId = roomFactory.generateRoom("TestUser1");

    File targetFile = new File("./files/test..jpg");
    MockMultipartFile image = new MockMultipartFile(
        "image", targetFile.getName(), "image/jpeg", new FileInputStream(targetFile));

    this.mockMvc.perform(
        RestDocumentationRequestBuilders.fileUpload("/rooms/{roomId}/images/{imageId}", roomId, 1)
            .file(image)
            .accept(MediaTypes.HAL_JSON)
            .header("Authorization", "Bearer " + accessToken)
    )
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andDo(print());
  }

  @Test
  @WithMockUser("TestUser1")
  @DisplayName("썸네일 이미지 업로드(실패)내 방이 아님")
  void uploadThumbnailImageFailBecauseNotMine() throws Exception {
    String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();

    File targetFile = new File("./files/thumbnailImg/test.jpg");
    MockMultipartFile image = new MockMultipartFile(
        "image", targetFile.getName(), "image/jpeg", new FileInputStream(targetFile));

    this.mockMvc.perform(
        RestDocumentationRequestBuilders.fileUpload("/rooms/{roomId}/images/{imageId}", 1, 1)
            .file(image)
            .accept(MediaTypes.HAL_JSON)
            .header("Authorization", "Bearer " + accessToken)
    )
        .andExpect(MockMvcResultMatchers.status().isForbidden())
        .andDo(print())
    ;
  }
}