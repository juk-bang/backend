package com.jukbang.api.community.entity;

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
public class Comments extends Time {
    @Id
    /**
     *  각 댓글의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    /**
     * 100자 이내의 댓글 입력
     */
    @Column(length = 100, nullable = false)
    private String body;


    @OneToOne
    @JoinColumn(name = "writer_id")
    private User writer;
}
