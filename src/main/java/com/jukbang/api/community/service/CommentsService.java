package com.jukbang.api.community.service;

import com.jukbang.api.community.dto.CommentsDto;
import com.jukbang.api.community.repository.CommentsRepository;
import com.jukbang.api.community.repository.CommunityRepository;
import com.jukbang.api.community.request.CreateCommentRequest;
import com.jukbang.api.community.request.UpdateCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final CommunityRepository communityRepository; // 댓글수 추가용

    /**
     * 댓글 리스트 (data) to List (Object)
     *
     * @param univId
     * @param postId
     * @return (List) boardDtoList
     */
    @Transactional
    public List<CommentsDto> getCommentsList(int univId, int postId) {
/*        List<Comments> boardEntities = commentsRepository.findAllByUnivIdAndPostId(univId, postId);
        List<CommentsDto> boardDtoList = new ArrayList<>();

        for (Comments boardEntity : boardEntities) {
            CommentsDto boardDTO = CommentsDto.builder()
                    .id(boardEntity.getId())
                    .postId(boardEntity.getPostId())
                    .writer(boardEntity.getWriter())
                    .modifiedDate(boardEntity.getModifiedDate())
                    .body(boardEntity.getBody())
                    .univId(boardEntity.getUnivId())
                    .build();

            boardDtoList.add(boardDTO);
        }

        return boardDtoList;*/
        return null;
    }

    /**
     * 댓글 달기 기능 (저장)
     * 입력해야될 데이터 : writter (작성자), body(내용)
     * @return
     */
    @Transactional
    public long SaveComment(int univId, int postId, CreateCommentRequest createCommentRequest) {
/*
        Optional<Community> communityWrapper = communityRepository.findById((long) postId);
        Community community = communityWrapper.get();

        CommunityDto communityDTO = CommunityDto.builder()
                .id(community.getId())
                .title(community.getTitle())
                .body(community.getBody())
                .writer(community.getWriter())
                .modifiedDate(community.getCreatedDate())
                .comments(community.getComments() + 1)
                .univId(community.getUnivId())
                .views(community.getViews())
                .build();
        communityRepository.save(communityDTO.toEntity());

        return commentsRepository.save(
                new Comments(
                        createCommentRequest.getId(),
                        createCommentRequest.getWriter(),
                        createCommentRequest.getBody(),
                        univId,
                        postId
                )).getId(); // 잘모르겠음*/
        return 0;
    }


    /**
     * 수정 하기
     * 댓글의 고유번호 (id) 에 접근하여 수정
     * @return
     */
    @Transactional
    public long updateComment(int univId, int postId, long id, UpdateCommentRequest updateCommentRequest) {
/*        return commentsRepository.save(
                new Comments(
                        id,
                        updateCommentRequest.getWriter(),
                        updateCommentRequest.getBody(),
                        univId,
                        postId
                )).getId();*/
        return id;
    }

    /**
     * 삭제하기
     * 댓글의 고유번호 (id) 에 접근하여 삭제
     */
    @Transactional
    public void deleteComment(long id, long postId) {
/*
        Optional<Community> communityWrapper = communityRepository.findById((long) postId);
        Community community = communityWrapper.get();

        CommunityDto communityDTO = CommunityDto.builder()
                .id(community.getId())
                .title(community.getTitle())
                .body(community.getBody())
                .writer(community.getWriter())
                .modifiedDate(community.getCreatedDate())
                .comments(community.getComments() - 1)
                .univId(community.getUnivId())
                .views(community.getViews())
                .build();
        communityRepository.save(communityDTO.toEntity()).getId();
        commentsRepository.deleteById(id)*/
        ;
    }
}

