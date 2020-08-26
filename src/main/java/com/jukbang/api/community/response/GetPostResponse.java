package com.jukbang.api.community.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class GetPostResponse {
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
