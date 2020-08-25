package com.jukbang.api.room.controller;

import com.jukbang.api.room.request.CreateReviewRequest;
import com.jukbang.api.room.request.UpdateReviewRequest;
import com.jukbang.api.room.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review/{univId}")
public class ReviewController {

    private final ReviewService ReviewService;

    /**
     * 각 방의 리뷰 GET
     *
     * @param univId
     * @param roomId
     * @return (List) reviewList
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/{roomId}")
    public List getReviewList(
            @PathVariable("univId") int univId,
            @PathVariable("roomId") int roomId
    ) {
        return ReviewService.getReviewList(univId, roomId);
    }

    /**
     * 각 방 리뷰 CREATE
     *
     * @param univId
     * @param roomId
     * @param createReviewRequest
     * @return (long) id
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/{roomId}")
    public long createReview(
            @PathVariable("univId") int univId,
            @PathVariable("roomId") int roomId,
            @RequestBody CreateReviewRequest createReviewRequest
    ) {
        return ReviewService.SaveReview(univId, roomId, createReviewRequest);
    }

    /**
     * 각 리뷰 UPDATE
     *
     * @param univId
     * @param roomId
     * @param reviewId
     * @param updateReviewRequest
     * @return (long) id
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/{roomId}/{reviewId}")
    public long updateReview(
            @PathVariable("univId") int univId,
            @PathVariable("roomId") int roomId,
            @PathVariable("reviewId") long reviewId,
            @RequestBody UpdateReviewRequest updateReviewRequest
    ) {
        return ReviewService.rewriteReview(univId, roomId, reviewId, updateReviewRequest);
    }

    /**
     * 리뷰 DELETE
     *
     * @param reviewId
     * @return (String) success
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{roomId}/{reviewId}")
    public String deleteReview(
            @PathVariable("roomId") long roomId,
            @PathVariable("reviewId") long reviewId
    ) {
        ReviewService.deleteComment(reviewId);
        return "success";
    }
}
