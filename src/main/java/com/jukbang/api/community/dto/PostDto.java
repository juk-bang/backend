package com.jukbang.api.community.dto;

import com.jukbang.api.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor

public class PostDto {
    private String writer;
    private String title;
    private String body;
    private int views;
    private LocalDateTime updatedDate;

    @Builder
    public PostDto(String title, User writer, String body, int views, LocalDateTime updatedDate) {
        this.title = title;
        this.writer = writer.getUserId();
        this.body = body;
        this.views = views;
        this.updatedDate = updatedDate;
    }


}
