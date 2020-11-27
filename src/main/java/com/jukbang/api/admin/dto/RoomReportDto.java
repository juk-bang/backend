package com.jukbang.api.admin.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomReportDto {
    private long reportRoomId;
    private String detail;
    private int type;
    private LocalDateTime updatedDate;

    @Builder
    public RoomReportDto(long reportRoomId, String detail, int type,LocalDateTime updatedDate){
        this.reportRoomId = reportRoomId;
        this.detail = detail;
        this.type= type;
        this.updatedDate = updatedDate;
    }
}
