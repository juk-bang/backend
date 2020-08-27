package com.jukbang.api.factory;

import com.jukbang.api.user.request.CreateReportRequest;
import com.jukbang.api.user.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportFactory {
    @Autowired
    protected ReportService reportService;

    public Long generateReport(int id) {
        return reportService.createReport(1, 1, "TestUser1", generateReportRequest(id));
    }

    public CreateReportRequest generateReportRequest(int id) {
        return CreateReportRequest.builder()
                .userid("TestUser1")
                .univid(1)
                .title("신고합니다.")
                .roomid(1)
                .id(id)
                .body("응 너 신고")
                .build();
    }

}
