package com.jukbang.api.room.service;

import com.jukbang.api.admin.entity.RoomReport;
import com.jukbang.api.admin.repository.RoomReportRepository;
import com.jukbang.api.room.repository.RoomRepository;
import com.jukbang.api.room.request.RoomReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RoomReportService {
    private final RoomReportRepository roomReportRepository;



    /**
     *  방 신고하기 기능
     */
    @Transactional
    public long reportRoom(
            long roomid,
            RoomReportRequest roomReportRequest
            ){

        return roomReportRepository.save(
                new RoomReport(
                        roomid,
                        roomReportRequest.getType(),
                        roomReportRequest.getDetail()
                )
        ).getRoomid();
    }
}
