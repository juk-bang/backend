package com.jukbang.api.user.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jukbang.api.user.dto.ReportDetailWrapper;
import com.jukbang.api.user.dto.ReportDto;
import com.jukbang.api.user.dto.ReportListWrapper;
import com.jukbang.api.user.entity.Report;
import com.jukbang.api.user.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * import org.springframework.web.bind.annotation.PathVariable;
 * import org.hibernate.criterion.Example;
 * import java.util.Optional;
 **/


@AllArgsConstructor
@Service

public class ReportService {
    private ReportRepository reportRepository;


    /**
     * 신고 하기 기능
     * 입력해야될 데이터 :
     */
    @Transactional
    public long SaveReport(int univid, int roomid, String userid, String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ReportDto reportDto;
        reportDto = objectMapper.readValue(json, ReportDto.class);
        reportDto.setUnivid(univid);
        reportDto.setRoomid(roomid);
        reportDto.setUserid(userid);


        return reportRepository.save(reportDto.toEntity()).getId();
    }

    @Transactional
    public List<ReportListWrapper> reportList(int univid) throws JsonProcessingException {

        List<Report> reportEntities = reportRepository.findAllByUnivid(univid);
        List<ReportListWrapper> reportList1 = new ArrayList<>();

        for (Report reportEntity : reportEntities) {
            ReportListWrapper reoprtDTO = ReportListWrapper.builder()
                    .id(reportEntity.getId())
                    .title(reportEntity.getTitle())
                    .writer(reportEntity.getUserid())
                    .modifiedDate(reportEntity.getCreatedDate())
                    .roomid(reportEntity.getRoomid())
                    .build();

            reportList1.add(reoprtDTO);
        }
        return reportList1;

    }

    @Transactional
    public ReportDetailWrapper reportDetail(long Univid, long Roomid, long Reportid) {
        Optional<Report> report = reportRepository.findById(Reportid);
        Report reports = report.get();
        ReportDetailWrapper reportdetail = ReportDetailWrapper.builder()
                .writer(reports.getUserid())
                .title(reports.getTitle())
                .body(reports.getBody())
                .modifiedDate(reports.getModifiedDate())
                .build();
        return reportdetail;
    }

    @Transactional
    public void deleteReport(long Reportid) {
        reportRepository.deleteById(Reportid);
    }


}

