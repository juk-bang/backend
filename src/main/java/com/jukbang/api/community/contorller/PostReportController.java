package com.jukbang.api.community.contorller;

import com.jukbang.api.community.CommunityRole;
import com.jukbang.api.community.request.PostReportRequest;
import com.jukbang.api.community.service.PostReportService;
import com.jukbang.api.room.request.RoomReportRequest;
import com.jukbang.api.room.service.RoomReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community/{role}/{univId}")
@RequiredArgsConstructor
public class PostReportController {

    private final PostReportService postReportService;
    /**
     *  신고하기
     */
    @PostMapping("/{postId}/report")
    public Long reportRoom(
            @PathVariable("postId") Long postId,
            @PathVariable("role") CommunityRole role,
            @PathVariable("univId") long univId,
            @RequestBody PostReportRequest postReportRequest
    ){
        return postReportService.reportPost(postId,univId, role, postReportRequest);
    }

}