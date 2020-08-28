package com.jukbang.api.community.service;

import com.jukbang.api.community.repository.CommunityRepository;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.request.UpdatePostRequest;
import com.jukbang.api.community.response.GetPostResponse;
import com.jukbang.api.room.dto.BoardlistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    @Transactional
    public List<BoardlistDto> getCommunitylist() {
/*        List<Community> boardEntities = communityRepository.findAll();
        List<BoardlistDto> boardDtoList = new ArrayList<>();

        for (Community boardEntity : boardEntities) {
            BoardlistDto boardDTO = BoardlistDto.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .writer(boardEntity.getWriter())
                    .modifiedDate(boardEntity.getCreatedDate())
                    .comments(boardEntity.getComments())
                    .univid(boardEntity.getUnivId())
                    .views(boardEntity.getViews())
                    .build();

            boardDtoList.add(boardDTO);
        }

        return boardDtoList;*/
        return null;
    }

    @Transactional
    public List<BoardlistDto> getMyPosts(String userid) {
/*        List<Community> boardEntities = communityRepository.findAllByWriter(userid);
        List<BoardlistDto> boardDtoList = new ArrayList<>();

        for (Community boardEntity : boardEntities) {
            BoardlistDto boardDTO = BoardlistDto.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .writer(boardEntity.getWriter())
                    .modifiedDate(boardEntity.getCreatedDate())
                    .comments(boardEntity.getComments())
                    .univid(boardEntity.getUnivId())
                    .views(boardEntity.getViews())
                    .build();

            boardDtoList.add(boardDTO);
        }
        return boardDtoList;*/
        return null;
    }

    @Transactional
    public Long SavePost(
            int univId,
            CreatePostRequest createPostRequest
    ) {
/*        return communityRepository.save(
                new Community(
                        createPostRequest.getId(),
                        createPostRequest.getTitle(),
                        createPostRequest.getWriter(),
                        createPostRequest.getBody(),
                        univId,
                        0,
                        0
                )).getId();*/
        return null;
    }

    @Transactional
    public GetPostResponse getPost(int univId, Long Postid) {
/*        List<BoardlistDto> list = getCommunitylist();
        long priv = 0, next = 0, tmp = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(0).getId() == Postid) {
                if (list.size() == 1)
                    next = 0;
                else
                    next = list.get(1).getId();
                break;
            }
            priv = list.get(i).getId();
            if (list.get(i + 1).getId() == Postid) {
                if (i + 2 == list.size())
                    next = 0;
                else
                    next = list.get(i + 2).getId();
                break;
            }
        }
        Optional<Community> communityWrapper = communityRepository.findById(Postid);
        Community community = communityWrapper.get();

        community.setViews(community.getViews() + 1);
        communityRepository.save(community);

        return GetPostResponse.builder()
                .id(community.getId())
                .previd(priv)
                .nextid(next)
                .title(community.getTitle())
                .body(community.getBody())
                .writer(community.getWriter())
                .modifiedDate(community.getCreatedDate())
                .comments(community.getComments())
                .univid(community.getUnivId())
                .views(community.getViews())
                .build();*/
        return null;
    }

    @Transactional
    public Long rewritePost(int univId, Long postId, UpdatePostRequest updatePostRequest) {
        return null;
/*        return communityRepository.save(
                new Community(
                        postId,
                        updatePostRequest.getTitle(),
                        updatePostRequest.getWriter(),
                        updatePostRequest.getBody(),
                        univId,
                        0,
                        0
                )).getId();*/
    }

    @Transactional
    public void deletePost(Long postid) {
        communityRepository.deleteById(postid);
    }
}
