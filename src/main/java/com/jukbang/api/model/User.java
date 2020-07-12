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

public class User extends Time {
    @Id
    /**
     *  회원의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    /**
     * 회원의 id
     */
    @Column(length = 30, nullable = false)
    private String userId;


    /**
     * 대학 번호
     */
    @Column(nullable = false)
    private int univid;


    @Builder
    public User(long id, String userId, int univid) {
        this.id = id;
        this.userId = userId;
        this.univid = univid;
    }
}
