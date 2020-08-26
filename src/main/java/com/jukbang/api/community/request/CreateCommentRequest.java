package com.jukbang.api.community.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentRequest {
    private long id;
    private String writer;
    private String body;
    private int univid;
    private int postid;
    private LocalDateTime modifiedDate;
}
