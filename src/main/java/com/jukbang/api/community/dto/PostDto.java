package com.jukbang.api.Post.dto;

import com.jukbang.api.community.entity.Post;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDto {
    private long id;
    private long previd;
    private long nextid;
    private String title;
    private String writer;
    private String body;
    private int univId;
    private int views;
    private int comments;
    private LocalDateTime modifiedDate;

/*    public Post toEntity() {
        Post build = Post.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .body(body)
                .univId(univId)
                .views(views)
                .comments(comments)
                .build();
        return build;
    }

    public class BoardDetailDto {
        private long id;
        private String title;
        private String writer;
        private int views;
        private int comments;

        public Post toEntity() {
            Post build = Post.builder()
                    .id(id)
                    .title(title)
                    .writer(writer)
                    .views(views)
                    .comments(comments)
                    .build();
            return build;
        }
    }*/

    @Builder
    public PostDto(long id, long previd, long nextid, String title, String writer, String body, int univId, int views, int comments, LocalDateTime modifiedDate) {
        this.id = id;
        this.previd = previd;
        this.nextid = nextid;
        this.title = title;
        this.writer = writer;
        this.body = body;
        this.univId = univId;
        this.views = views;
        this.comments = comments;
        this.modifiedDate = modifiedDate;
    }


}
