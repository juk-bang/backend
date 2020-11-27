package com.jukbang.api.admin.entity;


import com.jukbang.api.common.entity.Time;
import com.jukbang.api.community.CommunityRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class PostReport extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postReportId;

    @Column(nullable = false)
    private long postId;

    @Column(nullable = false)
    private long univId;

    @Column(nullable = false)
    private int type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String detail;


    @Column (nullable = false)
    private CommunityRole role;

    public PostReport(long postId, long univId,int type, String detail,CommunityRole role){
        this.postId = postId;
        this.univId = univId;
        this.type = type;
        this.detail = detail;
        this.role = role;
    }
}
