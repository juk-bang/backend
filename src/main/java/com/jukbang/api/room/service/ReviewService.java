package com.jukbang.api.room.service;

import com.jukbang.api.room.dto.ReviewDto;
import com.jukbang.api.room.entity.Review;
import com.jukbang.api.room.exception.ReviewNotFoundException;
import com.jukbang.api.room.repository.ReviewRepository;
import com.jukbang.api.room.request.CreateReviewRequest;
import com.jukbang.api.room.request.UpdateReviewRequest;
import com.jukbang.api.user.exception.UserNotFoundException;
import com.jukbang.api.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
  private final ReviewRepository reviewRepository;
  private final UserRepository userRepository;


  /**
   * Review list 에 표시되야할 댓글 정보 : writter (작성자),title(제목), body(내용) ,score(점수), date(작성 날짜)
   */
  @Transactional
  public List<ReviewDto> getReviewList(long roomId) {
    List<Review> reviews = reviewRepository.findAllByRoomId(roomId);
    List<ReviewDto> reviewDtoList = new ArrayList<>();

    for (Review review: reviews) {
      reviewDtoList.add(new ReviewDto(review));
    }

    return reviewDtoList;
  }

  /**
   * 댓글 달기 기능 (저장)
   * 입력해야될 데이터 : writter (작성자), body(내용), title(제목), score(점수)
   */
  @Transactional
  public Long SaveReview(long roomId, String userId, CreateReviewRequest createReviewRequest) {
    return reviewRepository.save(
        new Review(
            createReviewRequest.getMessage(),
            createReviewRequest.getGrade(),
            roomId,
            userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new)
        )
    ).getId();
  }


  /**
   * 수정 하기
   * 댓글의 고유번호 (id) 에 접근하여 수정
   */
  @Transactional
  public void rewriteReview(long reviewId, String userId, UpdateReviewRequest updateReviewRequest) {
    Review review = reviewRepository.findByIdAndWriter_UserId(reviewId, userId)
        .orElseThrow(ReviewNotFoundException::new);
    review.updateReview(updateReviewRequest.getGrade(), updateReviewRequest.getMessage());
  }

  /**
   * 삭제하기
   * 댓글의 고유번호 (id) 에 접근하여 삭제
   */
  @Transactional
  public void deleteComment(long reviewId, String userId) {
    Review review = reviewRepository.findByIdAndWriter_UserId(reviewId, userId)
        .orElseThrow(ReviewNotFoundException::new);
    reviewRepository.delete(review);
  }
}

