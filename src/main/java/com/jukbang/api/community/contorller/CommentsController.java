
package com.jukbang.api.community.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.community.dto.CommentsDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * import com.example.demo.repository.CommentsRepository;
 * import org.springframework.ui.Model;
 **/


@RestController
@AllArgsConstructor
@RequestMapping("community/comments/{univid}")
public class CommentsController {


    private com.jukbang.api.community.service.CommentsService CommentsService;


    /**
     * 게시글 별 전체 댓글 리스트 GET
     *
     * @param univid
     * @param postid
     * @return
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/{postid}")
    public List getCommentsList(
            @PathVariable("univid") int univid,
            @PathVariable("postid") int postid
    ) {
        List<CommentsDto> commentsList = CommentsService.getCommentsList(univid, postid);
        return commentsList;
    }


    /**
     * 댓글 CREATE
     *
     * @param univid
     * @param postid
     * @param json
     * @return (long) id
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/{postid}")
    public long createComment(
            @PathVariable("univid") int univid,
            @PathVariable("postid") int postid,
            @RequestBody String json
    ) throws JsonProcessingException {
        return CommentsService.SaveComment(univid, postid, json);

    }


    /**
     * 댓글 UPDATE
     *
     * @param univid
     * @param postid
     * @param id
     * @param json
     * @return (long) id
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/{postid}/{id}")
    public long updateComment(
            @PathVariable("univid") int univid,
            @PathVariable("postid") int postid,
            @PathVariable("id") long id,
            @RequestBody String json
    ) throws JsonProcessingException {
        return CommentsService.rewriteComment(univid, postid, id, json);
    }


    /**
     * 댓글 DELETE
     *
     * @param id
     * @param Postid
     * @return (String) success
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("{postid}/{id}")
    public String deleteComment(
            @PathVariable("id") long id,
            @PathVariable("postid") long Postid
    ) {
        CommentsService.deleteComment(id, Postid);
        return "success";
    }

}
