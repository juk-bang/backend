package com.jukbang.api.factory;

import com.jukbang.api.community.CreatePost;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.service.PostService;
import com.jukbang.api.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostFactory {

    @Autowired
    protected PostService postService;

    public Long generatePost(int univId, String userId) {
        return postService.savePost(univId, userId, generateCreatePostRequest());
    }

    public CreatePostRequest generateCreatePostRequest(){
        return CreatePostRequest.builder()
                .title("TestTitle")
                .body("TestBody")
                .build();
    }

}
