package com.jukbang.api.admin.dto;

import com.jukbang.api.community.CommunityRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostReportDto {
    private CommunityRole role;
    private long postId;
    private long reportPostId;
    private String detail;
    private int type;

    @Builder
    public PostReportDto(CommunityRole role,long postId,long reportPostId, String detail, int type){
        this.role = role;
        this.postId = postId;
        this.reportPostId = reportPostId;
        this.detail = detail;
        this.type= type;
    }
}
