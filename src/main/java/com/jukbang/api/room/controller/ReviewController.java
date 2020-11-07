package com.jukbang.api.room.controller;

import com.jukbang.api.room.request.CreateReviewRequest;
import com.jukbang.api.room.request.UpdateReviewRequest;
import com.jukbang.api.room.service.ReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

  private final ReviewService ReviewService;

  /**
   * 각 방의 리뷰 GET
   *
   * @param roomId
   * @return (List) reviewList
   */
  @GetMapping("/{roomId}")
  public List getReviewList(
      @PathVariable("roomId") long roomId
  ) {
    return ReviewService.getReviewList(roomId);
  }

  /**
   * 각 방 리뷰 CREATE
   *
   * @param roomId
   * @param createReviewRequest
   */
  @PostMapping("/{roomId}")
  public void createReview(
      @PathVariable("roomId") long roomId,
      @RequestBody CreateReviewRequest createReviewRequest
  ) {
    String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();

    ReviewService.SaveReview(roomId,requestUserId, createReviewRequest);
  }

  /**
   * 각 리뷰 UPDATE
   *
   * @param reviewId
   * @param updateReviewRequest
   */
  @PutMapping("/{reviewId}")
  public void updateReview(
      @PathVariable("reviewId") long reviewId,
      @RequestBody UpdateReviewRequest updateReviewRequest
  ) {
    String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();

    ReviewService.rewriteReview(reviewId, requestUserId, updateReviewRequest);
  }

  /**
   * 리뷰 DELETE
   *
   * @param reviewId
   */
  @DeleteMapping("/{reviewId}")
  public void deleteReview(
      @PathVariable("reviewId") long reviewId
  ) {
    String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();

    ReviewService.deleteComment(reviewId, requestUserId);
  }
}
