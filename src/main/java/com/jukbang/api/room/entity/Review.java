package com.jukbang.api.room.entity;

import com.jukbang.api.common.entity.Time;
import com.jukbang.api.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Review extends Time {
    @Id
    /**
     *  각 리뷰의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

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
    private int univId;

    /**
     * 방 정보 번호
     */
    @Column(nullable = false)
    private int roomId;

    /**
     * 리뷰 평점
     */
    @Column(nullable = false)
    private int score;

    @OneToOne
    @JoinColumn(name = "writer_id")
    private User writer;
}
