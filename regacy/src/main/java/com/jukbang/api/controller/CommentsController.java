
package com.jukbang.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.dto.CommentsDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * import com.example.demo.repository.CommentsRepository;
 * import org.springframework.ui.Model;
 **/


@RestController
@AllArgsConstructor
public class CommentsController {


    private com.jukbang.api.service.CommentsService CommentsService;


    /**
     * 한 게시글에 여러 댓글을 한번에 표현
     * <p>
     * ex)
     * 게시글 1)
     * 댓글 1
     * 댓글 2
     * ...
     * <p>
     * <p>
     * list 에 표시되야할 댓글 정보 : writter (작성자), body(내용)
     * <p>
     * 19.12.27 : univid별, postid별 나누어서 데이터 호출
     * 19.12.28 : date 호출 추가, delete에서 univid, postid 받아오기 기능 삭제
     */
    @CrossOrigin(origins = "*")
    @GetMapping("community/comments/{Univid}/{Postid}")
    public List Post(@PathVariable("Univid") int Univid, @PathVariable("Postid") int Postid) {
        List<CommentsDto> postdata = CommentsService.getCommentsList(Univid, Postid);
        return postdata;
    }


    /**
     * 댓글 쓰기 기능
     */
    @CrossOrigin(origins = "*")
    @PostMapping("community/comments/{Univid}/{Postid}")
    public long write(@PathVariable("Univid") int Univid, @PathVariable("Postid") int Postid, @RequestBody String json) throws JsonProcessingException {
        return CommentsService.SaveComment(Univid, Postid, json);

    }


    /**
     * 댓글 수정 기능
     * 댓글의 고유번호 (id) 에 접근하여 수정
     * (univid, postid 상관없이)
     */
    @CrossOrigin(origins = "*")
    @PutMapping("community/comments/{univid}/{postid}/{id}")
    public long update(@PathVariable("univid") int univid, @PathVariable("postid") int postid, @PathVariable("id") long id, @RequestBody String json) throws JsonProcessingException {
        return CommentsService.rewriteComment(univid, postid, id, json);
    }


    /**
     * 댓글 삭제 기능
     * 댓글의 고유번호 (id) 에 접근하여 삭제
     * (univid, postid 상관없이)
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("community/comments/{univid}/{postid}/{id}")
    public String delete(@PathVariable("id") long id, @PathVariable("postid") long Postid) {
        CommentsService.deleteComment(id, Postid);
        return "success";
    }

}
