
package com.example.demo.controller;

import com.example.demo.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 import com.example.demo.repository.CommentsRepository;
 import org.springframework.ui.Model;
 **/


@RestController
@AllArgsConstructor
public class ReportController {


    private ReportService reportService;


    /**
     *  신고 하기 기능
     */
    @CrossOrigin(origins = "*")
    @PostMapping("report/{univid}/{roomid}/{userid}")
    public long write(@PathVariable("univid") int univid,@PathVariable("roomid") int roomid,@PathVariable("userid") String userid, @RequestBody String json) throws JsonProcessingException {
        return reportService.SaveReport(univid,roomid,userid,json);

    }



}
