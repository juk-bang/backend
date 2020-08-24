package com.jukbang.api.room.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jukbang.api.room.dto.ReviewDto;
import com.jukbang.api.room.entity.Review;
import com.jukbang.api.room.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * import org.springframework.web.bind.annotation.PathVariable;
 * import org.hibernate.criterion.Example;
 * import java.util.Optional;
 **/


@AllArgsConstructor
@Service

public class ReviewService {
    private ReviewRepository reviewRepository;


    /**
     * Review list 에 표시되야할 댓글 정보 : writter (작성자),title(제목), body(내용) ,score(점수), date(작성 날짜)
     */
    @Transactional
    public List<ReviewDto> getReviewList(int univid, int roomid) {
        List<Review> boardEntities = reviewRepository.findAllByUnividAndRoomid(univid, roomid);
        List<ReviewDto> boardDtoList = new ArrayList<>();

        for (Review boardEntity : boardEntities) {
            ReviewDto boardDTO = ReviewDto.builder()
                    .id(boardEntity.getId())
                    .roomid(boardEntity.getRoomid())
                    .writer(boardEntity.getWriter())
                    .modifiedDate(boardEntity.getModifiedDate())
                    .body(boardEntity.getBody())
                    .title(boardEntity.getTitle())
                    .univid(boardEntity.getUnivid())
                    .score(boardEntity.getScore())
                    .build();

            boardDtoList.add(boardDTO);
        }

        return boardDtoList;
    }

    /**
     * 댓글 달기 기능 (저장)
     * 입력해야될 데이터 : writter (작성자), body(내용), title(제목), score(점수)
     */
    @Transactional
    public long SaveReview(int univid, int roomid, String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ReviewDto reviewDto;
        reviewDto = objectMapper.readValue(json, ReviewDto.class);
        reviewDto.setUnivid(univid);
        reviewDto.setRoomid(roomid);
        return reviewRepository.save(reviewDto.toEntity()).getId();
    }


    /**
     * 수정 하기
     * 댓글의 고유번호 (id) 에 접근하여 수정
     */
    @Transactional
    public long rewriteReview(int univid, int roomid, long id, String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ReviewDto reviewDto;
        reviewDto = objectMapper.readValue(json, ReviewDto.class);
        reviewDto.setUnivid(univid);
        reviewDto.setRoomid(roomid);
        reviewDto.setId(id);
        return reviewRepository.save(reviewDto.toEntity()).getId();
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

