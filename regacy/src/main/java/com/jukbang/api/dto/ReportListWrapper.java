package com.jukbang.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReportListWrapper {
    private long id;
    private long roomid;
    private String writer;
    private String title;
    private LocalDateTime modifiedDate;

    @Builder
    public ReportListWrapper(long id, long roomid, String title, String writer, LocalDateTime modifiedDate) {
        this.id = id;
        this.roomid = roomid;
        this.title = title;
        this.writer = writer;
        this.modifiedDate = modifiedDate;
    }
}
