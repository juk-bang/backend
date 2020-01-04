package com.example.demo.service;


import com.example.demo.dto.ReportDto;
import com.example.demo.repository.ReportRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 import org.springframework.web.bind.annotation.PathVariable;
 import org.hibernate.criterion.Example;
 import java.util.Optional;
 **/


import javax.transaction.Transactional;


@AllArgsConstructor
@Service

public class ReportService {
    private ReportRepository reportRepository;


    /**
     *   신고 하기 기능
     *   입력해야될 데이터 :
     */
    @Transactional
    public long SaveReport(int univid,int roomid,String userid,String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ReportDto reportDto;
        reportDto = objectMapper.readValue(json, ReportDto.class);
        reportDto.setUnivid(univid);
        reportDto.setRoomid(roomid);
        reportDto.setUserid(userid);



        return reportRepository.save(reportDto.toEntity()).getId();
    }


}

