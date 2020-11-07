package com.jukbang.api.factory;

import com.jukbang.api.community_student.CommunityRole;
import com.jukbang.api.community_student.request.CreatePostRequest;
import com.jukbang.api.community_student.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostFactory_student {

    @Autowired
    protected PostService postService;

    public Long generatePost(int univId, String userId) {
        return postService.savePost(univId, userId, CommunityRole.student, generateCreatePostRequest());
    }

    public CreatePostRequest generateCreatePostRequest(){
        return CreatePostRequest.builder()
                .title("TestTitle")
                .body("TestBody")
                .build();
    }

}
