package com.jukbang.api.community.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jukbang.api.community.dto.CommunityDto;
import com.jukbang.api.community.entity.Community;
import com.jukbang.api.community.repository.CommunityRepository;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.room.dto.BoardlistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    @Transactional
    public List<BoardlistDto> getCommunitylist() {
        List<Community> boardEntities = communityRepository.findAll();
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

        return boardDtoList;
    }

    @Transactional
    public List<BoardlistDto> getMyPosts(String userid) {
        List<Community> boardEntities = communityRepository.findAllByWriter(userid);
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
        return boardDtoList;
    }

    @Transactional
    public Long SavePost(
            int univId,
            CreatePostRequest createPostRequest
    ) {
        return communityRepository.save(
                new Community(
                        createPostRequest.getId(),
                        createPostRequest.getTitle(),
                        createPostRequest.getWriter(),
                        createPostRequest.getBody(),
                        univId,
                        0,
                        0
                )).getId();
    }

    @Transactional
    public CommunityDto getPost(int univId, Long Postid) {
        List<BoardlistDto> list = getCommunitylist();
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

        CommunityDto communityDTO = CommunityDto.builder()
                .id(community.getId())
                .previd(priv)
                .nextid(next)
                .title(community.getTitle())
                .body(community.getBody())
                .writer(community.getWriter())
                .modifiedDate(community.getCreatedDate())
                .comments(community.getComments())
                .univId(community.getUnivId())
                .views(community.getViews() + 1)
                .build();
        communityRepository.save(communityDTO.toEntity()).getId();

        return communityDTO;
    }

    @Transactional
    public Long rewritePost(int univId, Long postId, String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommunityDto communityDto;
        communityDto = objectMapper.readValue(json, CommunityDto.class);
        communityDto.setUnivId(univId);
        communityDto.setId(postId);
        return communityRepository.save(communityDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long postid) {
        communityRepository.deleteById(postid);
    }
}
