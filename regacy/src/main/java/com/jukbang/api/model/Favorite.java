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

public class Favorite {
    @Id
    /**
     *  각 댓글의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    /**
     * 회원
     */
    @Column(length = 30, nullable = false)
    private String userId;


    /**
     * 해당 회원의 찜 univid
     */
    @Column(nullable = false)
    private int univid;

    /**
     * 해당 회원의 찜 postid
     */
    @Column(nullable = false)
    private int roomid;


    @Builder
    public Favorite(long id, String userId, int univid, int roomid) {
        this.id = id;
        this.userId = userId;
        this.univid = univid;
        this.roomid = roomid;
    }
}
