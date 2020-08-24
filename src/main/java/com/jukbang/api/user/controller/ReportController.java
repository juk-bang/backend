
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
     * 신고 작성하기 CREATE
     *
     * @param univid
     * @param roomid
     * @param userid
     * @param json
     * @return (long) id
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PostMapping("report/{univid}/{roomid}/{userid}")
    public long createReport(
            @PathVariable("univid") int univid,
            @PathVariable("roomid") int roomid,
            @PathVariable("userid") String userid,
            @RequestBody String json
    ) throws JsonProcessingException {
        return reportService.createReport(univid, roomid, userid, json);

    }

    /**
     * 신고 list 출력 GET
     *
     * @param univid
     * @return (List) reportList
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @GetMapping("admin/report/{Univid}")
    public List<ReportListWrapper> reportList(
            @PathVariable("Univid") int univid
    ) throws JsonProcessingException {
        return reportService.reportList(univid);
    }

    /**
     * 신고 정보 상세보기 GET
     *
     * @param univid
     * @param Roomid
     * @param reportid
     * @return (ReportDetailWrapper) reportDetail
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @GetMapping("admin/report/{Univid}/{Roomid}/{Reportid}")
    public ReportDetailWrapper reportdetail(
            @PathVariable("Univid") long univid,
            @PathVariable("Roomid") long Roomid,
            @PathVariable("Reportid") long reportid
    ) throws JsonProcessingException {
        return reportService.reportDetail(univid, Roomid, reportid);
    }

    /**
     * 신고내용삭제 DELETE
     *
     * @param univid
     * @param Roomid
     * @param reportid
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("admin/report/{Univid}/{Roomid}/{Reportid}")
    public void deleteReport(
            @PathVariable("Univid") long univid,
            @PathVariable("Roomid") long Roomid,
            @PathVariable("Reportid") long reportid
    ) throws JsonProcessingException {
        reportService.deleteReport(reportid);
    }


}
