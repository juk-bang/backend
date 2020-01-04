package com.example.demo.dto;


import com.example.demo.model.Report;
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
    private int univid;
    private int roomid;
    private LocalDateTime creatDate;

    /**
     * class -> Entity
     */

    public Report toEntity() {
        Report build = Report.builder()
                .id(id)
                .userid(userid)
                .body(body)
                .title(title)
                .univid(univid)
                .roomid(roomid)
                .build();
        return build;
    }

    /**
     *  Entity -> class
     */

    @Builder
    public ReportDto(long id,String userid, String body,String title,int univid,int roomid,LocalDateTime creatDate) {
        this.id=id;
        this.userid=userid;
        this.body=body;
        this.title=title;
        this.univid=univid;
        this.roomid=roomid;
        this.creatDate = creatDate;
    }
}
