package com.jukbang.api.admin.dto;

import com.jukbang.api.community.CommunityRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostReportListDto {
    private CommunityRole role;
    private long postId;
    private long univId;
    private long reportPostId;
    private int type;
    private LocalDateTime updatedDate;

    @Builder
    PostReportListDto(CommunityRole role,long postId,long univId,long reportPostId, int type, LocalDateTime updatedDate){
        this.role = role;
        this.postId = postId;
        this.univId = univId;
        this.reportPostId = reportPostId;
        this.type = type;
        this.updatedDate = updatedDate;

    }
}
