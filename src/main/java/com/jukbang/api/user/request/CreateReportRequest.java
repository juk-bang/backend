package com.jukbang.api.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateReportRequest {
    private long id;
    private String userid;
    private String body;
    private String title;
    private int univid;
    private int roomid;
    private LocalDateTime creatDate;
}
