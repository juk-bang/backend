package com.jukbang.api.factory;

import com.jukbang.api.community.repository.PostRepository;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.service.PostService;
import com.jukbang.api.room.dto.*;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import com.jukbang.api.security.request.SignUpRequest;
import com.jukbang.api.security.response.SignInResponse;
import com.jukbang.api.security.service.AuthService;
import com.jukbang.api.user.UserRole;
import com.jukbang.api.user.entity.User;
import com.jukbang.api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PostFactory {

    @Autowired
    protected PostService postService;

    public Long generatePost(int univId, String userId) {
        return postService.savePost(univId,userId, generateCreatePostRequest());
    }

    public CreatePostRequest generateCreatePostRequest() {
        return CreatePostRequest.builder()
                .writer(new User())
                .title("TestTitle")
                .body("TestBody")
                .build();
    }

}
