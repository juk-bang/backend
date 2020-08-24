
package com.jukbang.api.room.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.room.dto.ReviewDto;
import com.jukbang.api.room.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * import com.example.demo.repository.CommentsRepository;
 * import org.springframework.ui.Model;
 **/


@RestController
@AllArgsConstructor
@RequestMapping("review/{Univid}")
public class ReviewController {


    private ReviewService ReviewService;


    /**
     * 각 방의 리뷰 GET
     *
     * @param univid
     * @param roomid
     * @return (List) reviewList
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/{Roomid}")
    public List getReviewList(
            @PathVariable("Univid") int univid,
            @PathVariable("Roomid") int roomid
    ) {
        List<ReviewDto> reviewList = ReviewService.getReviewList(univid, roomid);
        return reviewList;
    }


    /**
     * 각 방 리뷰 CREATE
     *
     * @param Univid
     * @param Roomid
     * @param json
     * @return (long) id
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/{Roomid}")
    public long createReview(
            @PathVariable("Univid") int univid,
            @PathVariable("Roomid") int roomid,
            @RequestBody String json
    ) throws JsonProcessingException {
        return ReviewService.SaveReview(univid, roomid, json);

    }


    /**
     * 각 리뷰 UPDATE
     *
     * @param univid
     * @param roomid
     * @param id
     * @param json
     * @return (long) id
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/{Roomid}/{id}")
    public long updateReview(
            @PathVariable("Univid") int univid,
            @PathVariable("Roomid") int roomid,
            @PathVariable("id") long id,
            @RequestBody String json
    ) throws JsonProcessingException {
        return ReviewService.rewriteReview(univid, roomid, id, json);
    }


    /**
     * 리뷰 DELETE
     *
     * @param id
     * @return (String) success
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{Roomid}/{id}")
    public String deleteReview(
            @PathVariable("id") long id
    ) {
        ReviewService.deleteComment(id);
        return "success";
    }

}
