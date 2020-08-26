package com.jukbang.api.user.controller;

import com.jukbang.api.user.dto.ReportDetailWrapper;
import com.jukbang.api.user.dto.ReportListWrapper;
import com.jukbang.api.user.request.CreateReportRequest;
import com.jukbang.api.user.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    /**
     * 신고 작성하기 CREATE
     *
     * @param univId
     * @param roomId
     * @param userId
     * @param createReportRequest
     * @return (long) id
     */
    @CrossOrigin(origins = "*")
    @PostMapping("report/{univId}/{roomId}/{userId}")
    public long createReport(
            @PathVariable("univId") int univId,
            @PathVariable("roomId") int roomId,
            @PathVariable("userId") String userId,
            @RequestBody CreateReportRequest createReportRequest
    ) {
        return reportService.createReport(univId, roomId, userId, createReportRequest);
    }

    /**
     * 신고 list 출력 GET
     *
     * @param univId
     * @return (List) reportList
     */
    @CrossOrigin(origins = "*")
    @GetMapping("admin/report/{univId}")
    public List<ReportListWrapper> reportList(
            @PathVariable("univId") int univId
    ) {
        return reportService.reportList(univId);
    }

    /**
     * 신고 정보 상세보기 GET
     *
     * @param univId
     * @param roomId
     * @param reportId
     * @return (ReportDetailWrapper) reportDetail
     */
    @CrossOrigin(origins = "*")
    @GetMapping("admin/report/{univId}/{roomId}/{reportId}")
    public ReportDetailWrapper reportDetail(
            @PathVariable("univId") long univId,
            @PathVariable("roomId") long roomId,
            @PathVariable("reportId") long reportId
    ) {
        return reportService.reportDetail(univId, roomId, reportId);
    }

    /**
     * 신고내용삭제 DELETE
     *
     * @param univId
     * @param roomId
     * @param reportId
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("admin/report/{univId}/{roomId}/{reportId}")
    public void deleteReport(
            @PathVariable("univId") long univId,
            @PathVariable("roomId") long roomId,
            @PathVariable("reportId") long reportId
    ) {
        reportService.deleteReport(reportId);
    }
}
