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
    private long postId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @Column(nullable = false)
    private int univId;

    @Column(nullable = false)
    private int views;

    @Column(nullable = false)
    private int comments;

    @OneToMany(mappedBy = "post")
    private List<Comments> commentsList;

    @OneToOne
    @JoinColumn(name = "writer_id")
    private User writer;

    public Post(String title, String body, User writer, int univId){
        this.title = title;
        this.body = body;
        this.writer = writer;
        this.univId = univId;
        this.views=0;
        this.comments=0;

    }

    public void updatePost(String title, String body){
        this.title = title;
        this.body= body;
    }

    public void addViews(int views){
        this.views = views+1;
    }

    public void addComments(int comments) { this.comments = comments + 1; }

    public void deleteComments(int comments) { this.comments = comments-1;}
}
