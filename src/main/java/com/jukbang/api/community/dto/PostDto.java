package com.jukbang.api.community.dto;

import com.jukbang.api.community.entity.Comments;
import com.jukbang.api.community.entity.Post;
import com.jukbang.api.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor

public class PostDto {
    private User writer;
    private String title;
    private String body;
    private int views;
    private LocalDateTime updatedDate;

    @Builder
    public PostDto(String title, User writer, String body, int views, LocalDateTime updatedDate) {
        this.title = title;
        this.writer = writer;
        this.body = body;
        this.views = views;
        this.updatedDate = updatedDate;
    }


}
