package com.jukbang.api.community.service;

import com.jukbang.api.admin.entity.PostReport;
import com.jukbang.api.admin.entity.RoomReport;
import com.jukbang.api.admin.repository.PostReportRepository;
import com.jukbang.api.admin.repository.RoomReportRepository;
import com.jukbang.api.community.CommunityRole;
import com.jukbang.api.community.request.PostReportRequest;
import com.jukbang.api.room.request.RoomReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostReportService {
    private final PostReportRepository postReportRepository;

    /**
     *  게시글 신고하기 기능
     */
    @Transactional
    public long reportPost(
            long postId,
            long univId,
            CommunityRole role,
            PostReportRequest postReportRequest
    ){

        return postReportRepository.save(
                new PostReport(
                        postId,
                        univId,
                        postReportRequest.getType(),
                        postReportRequest.getDetail(),
                        role
                )
        ).getPostReportId();
    }
}
