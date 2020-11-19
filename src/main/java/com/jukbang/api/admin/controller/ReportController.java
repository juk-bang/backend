package com.jukbang.api.admin.controller;


import com.jukbang.api.admin.dto.PostReportDto;
import com.jukbang.api.admin.dto.PostReportListDto;
import com.jukbang.api.admin.dto.RoomReportDto;
import com.jukbang.api.admin.dto.RoomReportListDto;
import com.jukbang.api.admin.service.ReportService;
import com.jukbang.api.community.CommunityRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/admin/report")
public class ReportController {

    private final ReportService reportService;

    /**
     * 방 관련 신고 리스트 (type 만 제공)
     * @return
     */
    @GetMapping("/rooms")
    public List<RoomReportListDto> getRoomReportList() {
        return reportService.getRoomReportList();
    }



    /**
     *  방 관련 신고 게시글 상세불러오기 (type , detail 제공)
     * @param roomId
     * @param roomReportId
     * @return
     */
    @GetMapping("/rooms/{roomId}/{roomReportId}")
    public RoomReportDto getRoomReport(
            @PathVariable("roomId") long roomId,
            @PathVariable("roomReportId") long roomReportId
    ){
        return reportService.getRoomReport(roomId,roomReportId);
    }

    @GetMapping("/community/{role}")
    public List<PostReportListDto> getPostReportList(
            @PathVariable("role") CommunityRole role
    ) {
        return reportService.getPostReportList(role);
    }

    @GetMapping("/community/{role}/{postId}/{postReportId}")
    public PostReportDto getPostRepost(
            @PathVariable("role") CommunityRole role,
            @PathVariable("postId") Long postId,
            @PathVariable("postReportId") long postReportId
    ){
        return reportService.getPostReport(role,postId,postReportId);
    }


}
