package com.jukbang.api.room.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateReviewRequest {
    private long id;
    private String writer;
    private String body;
    private int univid;
    private int roomid;
    private int score;
    private String title;
    private LocalDateTime modifiedDate;
}
