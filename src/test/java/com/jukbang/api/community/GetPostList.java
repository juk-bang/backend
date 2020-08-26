package com.jukbang.api.community;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.service.CommunityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetPostList extends BaseControllerTest {

    @Autowired
    private CommunityService communityService;

    @Test
    @DisplayName("전체게시글 리스트 불러오기(성공)")
    void GetPostListSuccess() throws Exception {
        CreatePostRequest createPostRequest = CreatePostRequest.builder()
                .id(1)
                .title("post1")
                .writer("writer1")
                .body("body body")
                .build();

        communityService.SavePost(1,createPostRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/community/{univId}",1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetPostList"))
                ;


    }



}