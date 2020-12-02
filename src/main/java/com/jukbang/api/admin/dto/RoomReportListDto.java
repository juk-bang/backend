package com.jukbang.api.admin.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomReportListDto {
    private long reportRoomId;
    private long roomId;
    private int type;
    private LocalDateTime updatedDate;

    @Builder
    RoomReportListDto(long reportRoomId, long roomId, int type, LocalDateTime updatedDate){
        this.reportRoomId = reportRoomId;
        this.roomId = roomId;
        this.type = type;
        this.updatedDate = updatedDate;
    }

}
