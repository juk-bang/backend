package com.jukbang.api.user.entity;

import com.jukbang.api.common.entity.Time;
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

public class Report extends Time {
    @Id
    /**
     *  각 신고 글의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    /**
     * 신고자 id
     */
    @Column(length = 30, nullable = false)
    private String userId;


    /**
     * 해당 신고글 의 univId
     */
    @Column(nullable = false)
    private int univId;

    /**
     * 해당 신고글 의 roomId
     */
    @Column(nullable = false)
    private int roomId;

    /**
     * 신고글 입력
     */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    /**
     * 100자 이내의 제목 입력
     */
    @Column(length = 100, nullable = false)
    private String title;


    @Builder
    public Report(long id, String userId, int univId, int roomId, String body, String title) {
        this.id = id;
        this.userId = userId;
        this.univId = univId;
        this.roomId = roomId;
        this.body = body;
        this.title = title;
    }
}
