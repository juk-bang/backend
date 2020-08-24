package com.jukbang.api.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReportDetailWrapper {
    private String writer;
    private String title;
    private String body;
    private LocalDateTime modifiedDate;

    @Builder
    public ReportDetailWrapper(String title, String writer, String body, LocalDateTime modifiedDate) {
        this.title = title;
        this.writer = writer;
        this.body = body;
        this.modifiedDate = modifiedDate;
    }
}
