package com.example.demo.model;

import lombok.*;

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
    @Column (length=30,nullable=false)
    private String userid;


    /**
     *  해당 신고글 의 univid
     */
    @Column(nullable=false)
    private int univid;

    /**
     *  해당 신고글 의 roomid
     */
    @Column(nullable=false)
    private int roomid;

    /**
     *  신고글 입력
     */
    @Column(columnDefinition = "TEXT",nullable = false)
    private String body;

    /**
     * 100자 이내의 제목 입력
     */
    @Column(length = 100, nullable =false)
    private String title;


    @Builder
    public Report(long id,String userid, int univid,int roomid, String body, String title) {
        this.id=id;
        this.userid=userid;
        this.univid = univid;
        this.roomid = roomid;
        this.body=body;
        this.title=title;
    }
}
