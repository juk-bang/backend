package com.jukbang.api.admin.dto;

import com.jukbang.api.community.CommunityRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostReportListDto {
    private CommunityRole role;
    private long reportPostId;
    private int type;

    @Builder
    PostReportListDto(CommunityRole role,long reportPostId, int type){
        this.role = role;
        this.reportPostId = reportPostId;
        this.type = type;
    }
}
