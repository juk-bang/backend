package com.jukbang.api.community.entity;

import com.jukbang.api.common.entity.Time;
import com.jukbang.api.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Post extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;
    @Column(nullable = false)
    private int univId;
    @Column(nullable = false)
    private int views;
    @Column(nullable = false)
    private int commentsNum;

    @OneToMany(mappedBy = "comments")
    private List<Comments> comments;

    @OneToOne
    @JoinColumn(name = "writer_id")
    private User writer;
}
