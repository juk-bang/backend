package com.jukbang.api.admin.dto;

import com.jukbang.api.community.CommunityRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class PostReportDto {
    private CommunityRole role;
    private long postId;
    private long reportPostId;
    private String detail;
    private int type;
    private LocalDateTime updatedDate;

    @Builder
    public PostReportDto(CommunityRole role,long postId,long reportPostId, String detail, int type, LocalDateTime updatedDate){
        this.role = role;
        this.postId = postId;
        this.reportPostId = reportPostId;
        this.detail = detail;
        this.type= type;
        this.updatedDate = updatedDate;
    }
}
