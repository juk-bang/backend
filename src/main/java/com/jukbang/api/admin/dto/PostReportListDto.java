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
    private long reportPostId;
    private int type;
    private LocalDateTime updatedDate;

    @Builder
    PostReportListDto(CommunityRole role,long reportPostId, int type, LocalDateTime updatedDate){
        this.role = role;
        this.reportPostId = reportPostId;
        this.type = type;
        this.updatedDate = updatedDate;

    }
}
