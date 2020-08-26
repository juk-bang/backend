package com.jukbang.api.community.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePostRequest {
    private long id;
    private long previd;
    private long nextid;
    private String title;
    private String writer;
    private String body;
    private int univid;
    private int views;
    private int comments;
    private LocalDateTime modifiedDate;
}
