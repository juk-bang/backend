package com.jukbang.api.file;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.file.data.ThumbnailImage;
import com.jukbang.api.file.data.ThumbnailImageRepository;
import com.jukbang.api.user.UserRole;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

@DisplayName("썸네일 테스트")
class DeleteThumbnailImageTest extends BaseControllerTest {
  @Autowired
  ThumbnailImageRepository thumbnailImageRepository;

  @Test
  @DisplayName("썸네일 이미지 받기(성공)")
  void getThumbnailImageSuccess() throws Exception {
    String accessToken = userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();
    Long roomId = roomFactory.generateRoom("TestUser1");
    File targetFile = new File("./files/thumbnailImg/test.jpg");
    InputStream stream = new FileInputStream(targetFile);
    Files.copy(Paths.get("./files/thumbnailImg/test.jpg"), Paths.get("./files/thumbnailImg/"+roomId+"-1.jpg"), StandardCopyOption.REPLACE_EXISTING);
    ThumbnailImage thumbnailImage = new ThumbnailImage(roomId + "-1", "./files/thumbnailImg/"+roomId+"-1.jpg");
    thumbnailImageRepository.save(thumbnailImage);

    this.mockMvc.perform(
        RestDocumentationRequestBuilders.delete("/rooms/{roomId}/images/{imageId}", roomId, 1)
            .header("Authorization", "Bearer " + accessToken)
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(document("deleteThumbnailImage"));
  }

  @Test
  @DisplayName("썸네일 이미지 받기(이미지가 없을 때)")
  void getThumbnailImageFailBecauseNotFound() throws Exception {
    String accessToken = userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();

    this.mockMvc.perform(delete("/rooms/{roomId}/images/{imageId}", 1, 1)
        .header("Authorization", "Bearer " + accessToken)
    )
        .andExpect(status().isNotFound())
        .andDo(print());
  }
}