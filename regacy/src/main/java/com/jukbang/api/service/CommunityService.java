package com.jukbang.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jukbang.api.dto.BoardlistDto;
import com.jukbang.api.dto.CommunityDto;
import com.jukbang.api.model.Community;
import com.jukbang.api.repository.CommunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommunityService {


    private CommunityRepository communityRepository;


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
                    .univid(boardEntity.getUnivid())
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
                    .univid(boardEntity.getUnivid())
                    .views(boardEntity.getViews())
                    .build();

            boardDtoList.add(boardDTO);
        }
        return boardDtoList;
    }

    @Transactional
    public Long SavePost(int Univid, String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommunityDto communityDto;
        communityDto = objectMapper.readValue(json, CommunityDto.class);
        communityDto.setUnivid(Univid);
        return communityRepository.save(communityDto.toEntity()).getId();
    }

    @Transactional
    public CommunityDto getPost(int Univid, Long Postid) {
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
                .univid(community.getUnivid())
                .views(community.getViews() + 1)
                .build();
        communityRepository.save(communityDTO.toEntity()).getId();

        return communityDTO;
    }

    @Transactional
    public Long rewritePost(int Univid, Long Postid, String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommunityDto communityDto;
        communityDto = objectMapper.readValue(json, CommunityDto.class);
        communityDto.setUnivid(Univid);
        communityDto.setId(Postid);
        return communityRepository.save(communityDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long Postid) {
        communityRepository.deleteById(Postid);
    }

}
