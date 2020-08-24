package com.jukbang.api.community.request;

import java.time.LocalDateTime;

public class CreateCommentRequest {
    private long id;
    private String writer;
    private String body;
    private int univid;
    private int postid;
    private LocalDateTime modifiedDate;
}
