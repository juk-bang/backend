package com.jukbang.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table

public class Comments extends Time {
    @Id
    /**
     *  각 댓글의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    /**
     * 댓글을 달 writer의 이름
     */
    @Column(length = 30, nullable = false)
    private String writer;


    /**
     * 100자 이내의 댓글 입력
     */
    @Column(length = 100, nullable = false)
    private String body;

    /**
     * 대학 번호
     */
    @Column(nullable = false)
    private int univid;

    /**
     * 게시글 번호
     */
    @Column(nullable = false)
    private int postid;


    @Builder
    public Comments(long id, String writer, String body, int univid, int postid) {
        this.id = id;
        this.writer = writer;
        this.body = body;
        this.univid = univid;
        this.postid = postid;

    }
}
