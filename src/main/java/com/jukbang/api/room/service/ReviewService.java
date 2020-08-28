package com.jukbang.api.room.service;

import com.jukbang.api.room.dto.ReviewDto;
import com.jukbang.api.room.entity.Review;
import com.jukbang.api.room.repository.ReviewRepository;
import com.jukbang.api.room.request.CreateReviewRequest;
import com.jukbang.api.room.request.UpdateReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;


    /**
     * Review list 에 표시되야할 댓글 정보 : writter (작성자),title(제목), body(내용) ,score(점수), date(작성 날짜)
     */
    @Transactional
    public List<ReviewDto> getReviewList(int univId, int roomId) {
/*        List<Review> boardEntities = reviewRepository.findAllByUnivIdAndRoomId(univId, roomId);
        List<ReviewDto> boardDtoList = new ArrayList<>();

        for (Review boardEntity : boardEntities) {
            ReviewDto boardDTO = ReviewDto.builder()
                    .id(boardEntity.getId())
                    .roomid(boardEntity.getRoomId())
                    .writer(boardEntity.getWriter())
                    .modifiedDate(boardEntity.getModifiedDate())
                    .body(boardEntity.getBody())
                    .title(boardEntity.getTitle())
                    .univid(boardEntity.getUnivId())
                    .score(boardEntity.getScore())
                    .build();

            boardDtoList.add(boardDTO);
        }

        return boardDtoList;*/
        return null;
    }

    /**
     * 댓글 달기 기능 (저장)
     * 입력해야될 데이터 : writter (작성자), body(내용), title(제목), score(점수)
     */
    @Transactional
    public Long SaveReview(int univId, int roomId, CreateReviewRequest createReviewRequest) {
/*
        return reviewRepository.save(
                new Review(
                        createReviewRequest.getId(),
                        createReviewRequest.getWriter(),
                        createReviewRequest.getBody(),
                        univId,
                        roomId,
                        createReviewRequest.getScore(),
                        createReviewRequest.getTitle()
                )).getId();*/
        return (long)0;
    }


    /**
     * 수정 하기
     * 댓글의 고유번호 (id) 에 접근하여 수정
     */
    @Transactional
    public void rewriteReview(int univId, int roomId, long id, UpdateReviewRequest updateReviewRequest) {
/*
        return reviewRepository.save(
                new Review(
                        id,
                        updateReviewRequest.getWriter(),
                        updateReviewRequest.getBody(),
                        univId,
                        roomId,
                        updateReviewRequest.getScore(),
                        updateReviewRequest.getTitle()
                )).getId();*/
    }

    /**
     * 삭제하기
     * 댓글의 고유번호 (id) 에 접근하여 삭제
     */
    @Transactional
    public void deleteComment(long id) {
        reviewRepository.deleteById(id);
    }
}

