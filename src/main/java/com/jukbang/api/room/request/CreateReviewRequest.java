package com.jukbang.api.room.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequest {
    private long id;
    private String writer;
    private String body;
    private int univid;
    private int roomid;
    private int score;
    private String title;
    private LocalDateTime modifiedDate;
}
