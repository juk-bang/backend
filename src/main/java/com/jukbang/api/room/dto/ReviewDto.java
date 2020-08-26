package com.jukbang.api.room.dto;


import com.jukbang.api.room.entity.Review;
import lombok.*;

import java.time.LocalDateTime;

// data to object

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewDto {
    private long id;
    private String writer;
    private String body;
    private int univid;
    private int roomid;
    private int score;
    private String title;
    private LocalDateTime modifiedDate;

    /**
     * class -> Entity
     */

    public Review toEntity() {
        Review build = Review.builder()
                .id(id)
                .writer(writer)
                .body(body)
                .univId(univid)
                .roomId(roomid)
                .score(score)
                .title(title)
                .build();
        return build;
    }

    /**
     * Entity -> class
     */

    @Builder
    public ReviewDto(long id, String writer, String body, int univid, int roomid, String title, int score, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.body = body;
        this.univid = univid;
        this.roomid = roomid;
        this.score = score;
        this.title = title;
        this.modifiedDate = modifiedDate;
    }
}
