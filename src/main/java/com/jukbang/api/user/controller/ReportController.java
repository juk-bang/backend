
package com.jukbang.api.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.user.dto.ReportDetailWrapper;
import com.jukbang.api.user.dto.ReportListWrapper;
import com.jukbang.api.user.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * import com.example.demo.repository.CommentsRepository;
 * import org.springframework.ui.Model;
 **/


@RestController
@AllArgsConstructor
public class ReportController {


    private ReportService reportService;


    /**
     * 신고 하기 기능
     */
    @CrossOrigin(origins = "*")
    @PostMapping("report/{univid}/{roomid}/{userid}")
    public long writereport(@PathVariable("univid") int univid, @PathVariable("roomid") int roomid, @PathVariable("userid") String userid, @RequestBody String json) throws JsonProcessingException {
        return reportService.SaveReport(univid, roomid, userid, json);

    }

    /**
     * 신고 리스트 출력
     */
    @CrossOrigin(origins = "*")
    @GetMapping("admin/report/{Univid}")
    public List<ReportListWrapper> reportlist(@PathVariable("Univid") int univid) throws JsonProcessingException {
        return reportService.reportList(univid);
    }

    /**
     * 신고 상세 받기
     */
    @CrossOrigin(origins = "*")
    @GetMapping("admin/report/{Univid}/{Roomid}/{Reportid}")
    public ReportDetailWrapper reportdetail(@PathVariable("Univid") long univid, @PathVariable("Roomid") long Roomid, @PathVariable("Reportid") long reportid) throws JsonProcessingException {
        return reportService.reportDetail(univid, Roomid, reportid);
    }

    /**
     * 신고 내용 삭제
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("admin/report/{Univid}/{Roomid}/{Reportid}")
    public void deletereport(@PathVariable("Univid") long univid, @PathVariable("Roomid") long Roomid, @PathVariable("Reportid") long reportid) throws JsonProcessingException {
        reportService.deleteReport(reportid);
    }


}
