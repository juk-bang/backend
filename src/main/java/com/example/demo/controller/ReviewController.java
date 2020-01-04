
package com.example.demo.controller;

import com.example.demo.dto.ReviewDto;
import com.example.demo.service.ReviewService;
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
public class ReviewController {


    private ReviewService ReviewService;


    /**
     * 한 방정보에 여러 리뷰를 한번에 표현
     *
     * ex)
     * 방정보 1)
     *         리뷰 1
     *         리뷰 2
     *         ...
     *
     *
     *  list 에 표시되야할 리뷰 정보 : writter (작성자), body(내용),title(제목), score(점수)
     *
     */
    @CrossOrigin(origins = "*")
    @GetMapping("review/{Univid}/{Roomid}")
    public List Post(@PathVariable("Univid")int univid, @PathVariable("Roomid") int roomid){
        List<ReviewDto> postdata = ReviewService.getReviewList(univid, roomid);
        return postdata;
    }


    /**
     *  리뷰 쓰기 기능
     */
    @CrossOrigin(origins = "*")
    @PostMapping("review/{Univid}/{Roomid}")
    public long write(@PathVariable("Univid") int Univid,@PathVariable("Roomid") int Roomid, @RequestBody String json) throws JsonProcessingException {
        return ReviewService.SaveReview(Univid,Roomid,json);

    }


    /**
     *  리뷰 수정 기능
     *  리뷰의 고유번호 (id) 에 접근하여 수정
     *  (univid, roomid 상관없이)
     */
    @CrossOrigin(origins = "*")
    @PutMapping("review/{Univid}/{Roomid}/{id}")
    public long update(@PathVariable("Univid")int univid,@PathVariable("Roomid")int roomid,@PathVariable("id")long id, @RequestBody String json) throws JsonProcessingException {
        return  ReviewService.rewriteReview(univid,roomid,id,json);
    }


    /**
     * 댓글 삭제 기능
     *  댓글의 고유번호 (id) 에 접근하여 삭제
     *  (univid, postid 상관없이)
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("review/{Univid}/{Roomid}/{id}")
    public String delete(@PathVariable("id")long id){
        ReviewService.deleteComment(id);
        return "success";
    }

}
