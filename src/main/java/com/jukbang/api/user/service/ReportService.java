package com.jukbang.api.user.service;

import com.jukbang.api.user.dto.ReportDetailWrapper;
import com.jukbang.api.user.dto.ReportListWrapper;
import com.jukbang.api.user.entity.Report;
import com.jukbang.api.user.repository.ReportRepository;
import com.jukbang.api.user.request.CreateReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    /**
     * 신고 하기 기능
     * 입력해야될 데이터 :
     */
    @Transactional
    public long createReport(int univId, int roomId, String userId, CreateReportRequest createReportRequest) {

        return reportRepository.save(
                new Report(
                        createReportRequest.getId(),
                        userId,
                        univId,
                        roomId,
                        createReportRequest.getBody(),
                        createReportRequest.getTitle()
                )).getId();
    }

    @Transactional
    public List<ReportListWrapper> reportList(int univId) {

        List<Report> reportEntities = reportRepository.findAllByUnivId(univId);
        List<ReportListWrapper> reportList1 = new ArrayList<>();

        for (Report reportEntity : reportEntities) {
            ReportListWrapper reoprtDTO = ReportListWrapper.builder()
                    .id(reportEntity.getId())
                    .title(reportEntity.getTitle())
                    .writer(reportEntity.getUserId())
                    .modifiedDate(reportEntity.getCreatedDate())
                    .roomId(reportEntity.getRoomId())
                    .build();

            reportList1.add(reoprtDTO);
        }
        return reportList1;

    }

    @Transactional
    public ReportDetailWrapper reportDetail(long univId, long roomId, long reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        Report reports = report.get();

        return ReportDetailWrapper.builder()
                .writer(reports.getUserId())
                .title(reports.getTitle())
                .body(reports.getBody())
                .modifiedDate(reports.getModifiedDate())
                .build();
    }

    @Transactional
    public void deleteReport(long reportId) {
        reportRepository.deleteById(reportId);
    }
}

