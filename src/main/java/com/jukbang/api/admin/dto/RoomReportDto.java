package com.jukbang.api.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomReportDto {
    private long reportRoomId;
    private String detail;
    private int type;

    @Builder
    public RoomReportDto(long reportRoomId, String detail, int type){
        this.reportRoomId = reportRoomId;
        this.detail = detail;
        this.type= type;
    }
}
