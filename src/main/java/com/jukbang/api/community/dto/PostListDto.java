package com.jukbang.api.community_student.dto;

import com.jukbang.api.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostListDto {
    private long postId;
    private String title;
    private String writer;
    private int views;
    private int comments;
    private LocalDateTime updatedDate;


    @Builder
    public PostListDto(long postId, String title, User writer, int views,int comments, LocalDateTime updatedDate) {
        this.postId = postId;
        this.title = title;
        this.writer = writer.getUserId();
        this.views = views;
        this.comments = comments;
        this.updatedDate = updatedDate;
    }
}