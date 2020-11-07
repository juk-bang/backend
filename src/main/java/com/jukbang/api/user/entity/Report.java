package com.jukbang.api.user.entity;

import com.jukbang.api.common.entity.Time;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Report extends Time {
    @Id
    /**
     *  각 신고 글의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportId;

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

    @OneToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;

}
