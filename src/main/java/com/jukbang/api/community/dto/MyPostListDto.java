package com.jukbang.api.community.dto;


import com.jukbang.api.community.CommunityRole;
import com.jukbang.api.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MyPostListDto {
    private long postId;
    private String title;
    private int views;
    private int comments;
    private LocalDateTime updatedDate;
    private CommunityRole role;


    @Builder
    public MyPostListDto(long postId, String title, int views, int comments,CommunityRole role, LocalDateTime updatedDate) {
        this.postId = postId;
        this.title = title;
        this.views = views;
        this.comments = comments;
        this.role = role;
        this.updatedDate = updatedDate;
    }
}
