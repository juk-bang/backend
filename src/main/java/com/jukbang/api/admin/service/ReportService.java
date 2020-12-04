package com.jukbang.api.admin.service;


import com.jukbang.api.admin.dto.PostReportDto;
import com.jukbang.api.admin.dto.PostReportListDto;
import com.jukbang.api.admin.dto.RoomReportDto;
import com.jukbang.api.admin.dto.RoomReportListDto;
import com.jukbang.api.admin.entity.PostReport;
import com.jukbang.api.admin.entity.RoomReport;
import com.jukbang.api.admin.exception.ReportNotFoundException;
import com.jukbang.api.admin.repository.PostReportRepository;
import com.jukbang.api.admin.repository.RoomReportRepository;
import com.jukbang.api.community.CommunityRole;
import com.jukbang.api.community.dto.PostListDto;
import com.jukbang.api.community.entity.Post;
import com.jukbang.api.community.exception.PostNotFoundException;
import com.jukbang.api.community.repository.PostRepository;
import com.jukbang.api.community.service.PostService;
import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.exception.RoomNotFoundException;
import com.jukbang.api.room.repository.RoomRepository;
import com.jukbang.api.room.request.RoomReportRequest;
import com.jukbang.api.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final RoomReportRepository roomReportRepository;
    private final PostReportRepository postReportRepository;
    private final RoomRepository roomRepository;
    private final PostRepository postRepository;
    private final RoomService roomService;
    private final PostService postService;
    /**
     * 방_리폿 목록 불러오기 기능
     *
     * @return List<RoomReportListDto>
    **/
    @Transactional
    public List<RoomReportListDto> getRoomReportList() {
        List<RoomReport> reportEntities = roomReportRepository.findAll();

        List<RoomReportListDto> roomReportListDto_List= new ArrayList<>();

        for (RoomReport reportEntity : reportEntities) {
            RoomReportListDto roomReportListDTO = RoomReportListDto.builder()
                    .reportRoomId(reportEntity.getRoomReportId())
                    .roomId(reportEntity.getRoomId())
                    .type(reportEntity.getType())
                    .updatedDate(reportEntity.getModifiedDate())
                    .build();

            roomReportListDto_List.add(roomReportListDTO);
        }

        return roomReportListDto_List;
    }

    @Transactional
    public RoomReportDto getRoomReport(Long roomId, Long roomReportId){
        RoomReport roomReport = roomReportRepository.findByRoomIdAndRoomReportId(roomId,roomReportId).orElseThrow(ReportNotFoundException::new);

        RoomReportDto roomReportDto =RoomReportDto.builder()
                .detail(roomReport.getDetail())
                .type(roomReport.getType())
                .reportRoomId(roomId)
                .updatedDate(roomReport.getModifiedDate())
                .build();

        return roomReportDto;
    }

    @Transactional
    public List<PostReportListDto> getPostReportList (CommunityRole role){
        List<PostReport> reportEntities = postReportRepository.findAllByRole(role);

        List<PostReportListDto> postReportListDto_List= new ArrayList<>();

        for (PostReport reportEntity : reportEntities) {
            PostReportListDto postReportListDTO = PostReportListDto.builder()
                    .role(reportEntity.getRole())
                    .postId(reportEntity.getPostId())
                    .univId(reportEntity.getUnivId())
                    .reportPostId(reportEntity.getPostReportId())
                    .type(reportEntity.getType())
                    .updatedDate(reportEntity.getModifiedDate())
                    .build();

            postReportListDto_List.add(postReportListDTO);
        }

        return postReportListDto_List;
    }

    @Transactional
    public PostReportDto getPostReport(CommunityRole role, Long postId,Long postReportId){
        PostReport postReport = postReportRepository.findByRoleAndPostReportId(role,postReportId).orElseThrow(ReportNotFoundException::new);

        PostReportDto postReportDto = PostReportDto.builder()
                .role(role)
                .postId(postId)
                .univId(postReport.getUnivId())
                .reportPostId(postReportId)
                .detail(postReport.getDetail())
                .type(postReport.getType())
                .updatedDate(postReport.getModifiedDate())
                .build();

        return postReportDto;
    }

    @Transactional
    public void deleteReportRoom(Long reportid){
        roomReportRepository.deleteById(reportid);
    }

    @Transactional
    public void deleteReportPost(Long reportid){
        postReportRepository.deleteById(reportid);
    }

    @Transactional
    public void deleteRoom(Long roomid, Long reportid){
        Room room = roomRepository.findByRoomId(roomid).orElseThrow(RoomNotFoundException::new);
        roomReportRepository.deleteById(reportid);
        roomRepository.deleteById(roomid);
    }

    @Transactional
    public void deletePost(Long postid, Long reportid){
        postRepository.findByPostId(postid).orElseThrow(PostNotFoundException::new);
        postReportRepository.deleteById(reportid);
        postRepository.deleteById(postid);
    }
}
