package com.jukbang.api.user.dto;


import com.jukbang.api.user.entity.Report;
import lombok.*;

import java.time.LocalDateTime;

// data to object

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReportDto {
    private long id;
    private String userid;
    private String body;
    private String title;
    private int univId;
    private int roomId;
    private LocalDateTime creatDate;

    /**
     * class -> Entity
     */

    public Report toEntity() {
        Report build = Report.builder()
                .id(id)
                .userId(userid)
                .body(body)
                .title(title)
                .univId(univId)
                .roomId(roomId)
                .build();
        return build;
    }

    /**
     * Entity -> class
     */

    @Builder
    public ReportDto(long id, String userid, String body, String title, int univId, int roomId, LocalDateTime creatDate) {
        this.id = id;
        this.userid = userid;
        this.body = body;
        this.title = title;
        this.univId = univId;
        this.roomId = roomId;
        this.creatDate = creatDate;
    }
}
