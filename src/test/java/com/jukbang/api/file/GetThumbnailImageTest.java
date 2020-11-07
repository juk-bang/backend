package com.jukbang.api.file;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.file.data.ThumbnailImage;
import com.jukbang.api.file.data.ThumbnailImageRepository;
import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

@DisplayName("썸네일 테스트")
class GetThumbnailImageTest extends BaseControllerTest {
  @Autowired
  ThumbnailImageRepository thumbnailImageRepository;

  @Test
  @DisplayName("썸네일 이미지 받기(성공)")
  void getThumbnailImageSuccess() throws Exception {
    userFactory.generateUser(1);
    Long roomId = roomFactory.generateRoom("TestUser1");
    File targetFile = new File("./files/thumbnailImg/test.jpg");

    ThumbnailImage thumbnailImage = new ThumbnailImage(roomId + "-1", targetFile.getPath());
    thumbnailImageRepository.save(thumbnailImage);

    this.mockMvc.perform(
        RestDocumentationRequestBuilders.get("/rooms/{roomId}/images/{imageId}", roomId, 1))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(document("getThumbnailImage"));
  }

  @Test
  @DisplayName("썸네일 이미지 받기(이미지가 없을 때)")
  void getThumbnailImageFailBecauseNotFound() throws Exception {

    this.mockMvc.perform(get("/rooms/{roomId}/images/{imageId}", 1,1))
        .andExpect(status().isNotFound())
        .andDo(print());
  }
}