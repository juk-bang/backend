package com.jukbang.api.user.request;

import java.time.LocalDateTime;

public class CreateReportRequest {
    private long id;
    private String userid;
    private String body;
    private String title;
    private int univid;
    private int roomid;
    private LocalDateTime creatDate;
}
