package com.jukbang.api.community.service;

import com.jukbang.api.community.dto.CommentsDto;
import com.jukbang.api.community.entity.Comments;
import com.jukbang.api.community.entity.Post;
import com.jukbang.api.community.exception.CommentsNotFoundException;
import com.jukbang.api.community.exception.PostNotFoundException;
import com.jukbang.api.community.repository.CommentsRepository;
import com.jukbang.api.community.repository.PostRepository;
import com.jukbang.api.community.request.CreateCommentRequest;
import com.jukbang.api.community.request.UpdateCommentRequest;
import com.jukbang.api.user.exception.UserNotFoundException;
import com.jukbang.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    /**
     * 댓글 목록 불러오기 기능
     *
     * @param postId
     *
     * @return List<CommentsDto>
     */
    @Transactional
    public List<CommentsDto> getCommentsList(Long postId) {
        List<Comments> commentsEntities = commentsRepository.findAllByPost(
                                                                postRepository.findByPostId(postId).orElseThrow(PostNotFoundException::new)
                                                                );

        List<CommentsDto> commentsDtoList = new ArrayList<>();

        for (Comments commentsEntity : commentsEntities) {
            CommentsDto commentsDTO = CommentsDto.builder()
                    .commentsId(commentsEntity.getCommentsId())
                    .writer(commentsEntity.getWriter().getUserId())
                    .updatedDate(commentsEntity.getModifiedDate())
                    .body(commentsEntity.getBody())
                    .build();

            commentsDtoList.add(commentsDTO);
        }

        return commentsDtoList;
    }

    /**
     * 댓글 쓰기 기능 (저장하기)
     *
     * @param postId
     * @param createCommentRequest
     *
     * @return (long) commentsId
     */
    @Transactional
    public long saveComment(Long postId,
                            String userId,
                            CreateCommentRequest createCommentRequest ) {

        // 존재하는 게시글 인지 확인
        Post post = postRepository.findByPostId(postId).orElseThrow(PostNotFoundException::new);

        // 게시글 댓글 수  +1
        post.addComments();


        return commentsRepository.save(
                new Comments(
                        createCommentRequest.getBody(),
                        userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new),
                        post
                )).getCommentsId();
    }



    /**
     * 댓글 수정하기 기능
     *
     * @param postId
     * @param commentsId
     * @param updateCommentRequest
     *
     * @return (long) commentsId
     */
    @Transactional
    public long updateComment(Long postId,
                              Long commentsId,String userId,UpdateCommentRequest updateCommentRequest) {


        // 존재하는 Id 인지 확인
        userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);

        // 존재하는 게시글 인지 확인
        Post post = postRepository.findByPostId(postId).orElseThrow(PostNotFoundException::new);

        // 존재하는 댓글 인지 확인
        Comments comments = commentsRepository.findByCommentsId(commentsId).orElseThrow(CommentsNotFoundException::new);

        comments.updateComments(updateCommentRequest.getBody());

        return comments.getCommentsId();
    }




    /**
     * 댓글 삭제하기 기능
     *
     * @param postId
     * @param commentsId
     */
    @Transactional
    public void deleteComment(Long postId, Long commentsId, String userId) {

        // 존재하는 Id 인지 확인
        userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);

        // 존재하는 게시글 인지 확인
        Post post = postRepository.findByPostId(postId).orElseThrow(PostNotFoundException::new);

        // 존재하는 게시글 댓글 인지 확인
        commentsRepository.findByCommentsId(commentsId).orElseThrow(CommentsNotFoundException::new);

        // 게시글 댓글 수  -1
        post.deleteComments();

        commentsRepository.deleteById(commentsId);
    }
}

