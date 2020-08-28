package com.jukbang.api.community.dto;


import com.jukbang.api.community.entity.Comments;
import lombok.*;

import java.time.LocalDateTime;

// data to object

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentsDto {
    private long id;
    private String writer;
    private String body;
    private int univId;
    private int postId;
    private LocalDateTime modifiedDate;

    /**
     * class -> Entity
     */

/*    public Comments toEntity() {
        Comments build = Comments.builder()
                .id(id)
                .writer(writer)
                .body(body)
                .univId(univId)
                .postId(postId)
                .build();
        return build;
    }*/

    /**
     * Entity -> class
     */

    @Builder
    public CommentsDto(long id, String writer, String body, int univId, int postId, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.body = body;
        this.univId = univId;
        this.postId = postId;
        this.modifiedDate = modifiedDate;
    }
}
