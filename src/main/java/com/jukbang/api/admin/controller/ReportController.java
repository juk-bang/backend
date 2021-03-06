package com.jukbang.api.admin.controller;


import com.jukbang.api.admin.dto.PostReportDto;
import com.jukbang.api.admin.dto.PostReportListDto;
import com.jukbang.api.admin.dto.RoomReportDto;
import com.jukbang.api.admin.dto.RoomReportListDto;
import com.jukbang.api.admin.service.ReportService;
import com.jukbang.api.community.CommunityRole;
import com.jukbang.api.community.service.PostService;
import com.jukbang.api.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/admin/report")
public class ReportController {

    private final ReportService reportService;
    private final RoomService roomService;
    private final PostService postService;

    /**
     * 방 관련 신고 리스트 (type 만 제공)
     * @return
     */
    @GetMapping("/rooms")
    public List<RoomReportListDto> getRoomReportList() {
        return reportService.getRoomReportList();
    }



    /**
     *  방 관련 신고 상세불러오기 (type , detail 제공)
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

    /**
     *  커뮤니티 게시글 관련 신고 리스트 불러오기
     * @param role
     * @return
     */
    @GetMapping("/community/{role}")
    public List<PostReportListDto> getPostReportList(
            @PathVariable("role") CommunityRole role
    ) {
        return reportService.getPostReportList(role);
    }

    /**
     *  커뮤니티 게시글 관련 신고 상세 불러오기
     * @param role
     * @param postId
     * @param postReportId
     * @return
     */
    @GetMapping("/community/{role}/{postId}/{postReportId}")
    public PostReportDto getPostRepost(
            @PathVariable("role") CommunityRole role,
            @PathVariable("postId") Long postId,
            @PathVariable("postReportId") long postReportId
    ){
        return reportService.getPostReport(role,postId,postReportId);
    }


    /**
     *  방 신고 삭제
     * @param roomid
     * @param reportid
     */
    @DeleteMapping("/rooms/{roomid}/{reportid}")
    public String deleteRoom(
            @PathVariable("roomid") Long roomid,
            @PathVariable("reportid") Long reportid
    ){
        reportService.deleteRoom(roomid,reportid);
        return "success";
    }


    /**
     *  방 신고 , 방 잔류
     * @param reportid
     */
    @DeleteMapping("/rooms/{reportid}")
    public String deleteReportRoom(
            @PathVariable("reportid") Long reportid
    ){

        reportService.deleteReportRoom(reportid);
        return "success";
    }


    /**
     *  게시글 신고 삭제
     * @param postid
     * @param reportid
     */
    @DeleteMapping("/community/{postid}/{reportid}")
    public String deletePost(
            @PathVariable("postid") Long postid,
            @PathVariable("reportid") Long reportid
    ){

        reportService.deletePost(postid,reportid);
        return "success";
    }

    /**
     *  게시글 신고 , 게시글 잔류
     * @param reportid
     */
    @DeleteMapping("/community/{reportid}")
    public String deleteReportPost(
            @PathVariable("reportid") Long reportid
    ){

        reportService.deleteReportPost(reportid);
        return "success";
    }
}
