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

public class Review extends Time {
    @Id
    /**
     *  각 리뷰의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    /**
     * 리뷰를 달 writer의 이름
     */
    @Column(length = 30, nullable = false)
    private String writer;

    /**
     * 리뷰의 title이름
     */
    @Column(length = 30, nullable = false)
    private String title;


    /**
     * 100자 이내의 리뷰 입력
     */
    @Column(length = 100, nullable = false)
    private String body;

    /**
     * 대학 번호
     */
    @Column(nullable = false)
    private int univid;

    /**
     * 방 정보 번호
     */
    @Column(nullable = false)
    private int roomid;

    /**
     * 리뷰 평점
     */
    @Column(nullable = false)
    private int score;


    @Builder
    public Review(long id, String writer, String body, int univid, int roomid, int score, String title) {
        this.id = id;
        this.writer = writer;
        this.body = body;
        this.univid = univid;
        this.roomid = roomid;
        this.score = score;
        this.title = title;

    }
}
