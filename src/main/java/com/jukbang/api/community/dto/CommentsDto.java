package com.jukbang.api.community.dto;


import lombok.*;

import java.time.LocalDateTime;

// data to object

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentsDto {
    private long commentsId;
    private String writer;
    private String body;
    private LocalDateTime updatedDate;

    @Builder
    public CommentsDto(long commentsId, String writer, String body, LocalDateTime updatedDate) {
        this.commentsId = commentsId;
        this.writer = writer;
        this.body = body;
        this.updatedDate = updatedDate;
    }
}
