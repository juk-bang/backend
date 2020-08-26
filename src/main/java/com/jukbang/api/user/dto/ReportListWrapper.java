package com.jukbang.api.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReportListWrapper {
    private long id;
    private long roomId;
    private String writer;
    private String title;
    private LocalDateTime modifiedDate;

    @Builder
    public ReportListWrapper(long id, long roomId, String title, String writer, LocalDateTime modifiedDate) {
        this.id = id;
        this.roomId = roomId;
        this.title = title;
        this.writer = writer;
        this.modifiedDate = modifiedDate;
    }
}
