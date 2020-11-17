package com.jukbang.api.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomReportListDto {
    private long reportRoomId;
    private int type;

    @Builder
    RoomReportListDto(long reportRoomId, String detail, int type){
        this.reportRoomId = reportRoomId;
        this.type = type;
    }

}
