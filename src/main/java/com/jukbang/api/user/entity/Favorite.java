package com.jukbang.api.user.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
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
     * 해당 회원의 찜 postid
     */
    @Column(nullable = false)
    private int roomid;
}
