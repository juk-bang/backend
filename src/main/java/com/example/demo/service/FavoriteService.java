package com.example.demo.service;


import com.example.demo.dto.FavoriteDto;
import com.example.demo.model.Favorite;
import com.example.demo.model.Room;
import com.example.demo.repository.FavoriteRepository;
import com.example.demo.repository.RoomRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 import org.springframework.web.bind.annotation.PathVariable;
 import org.hibernate.criterion.Example;
 import java.util.Optional;
 **/


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service

public class FavoriteService {
    private FavoriteRepository favoriteRepository;
    private RoomRepository roomRepository;

    /**
     *  회원의 찜 목록 출력
     */
    @Transactional
    public List<Room> getFavoriteList(String userid) {
        List<Favorite> boardEntities = favoriteRepository.findAllByUserId(userid);
        List<Room> roomList = new ArrayList<>();
        for ( Favorite boardEntity : boardEntities) {
                Optional<Room> room = roomRepository.findById((long)boardEntity.getRoomid());
                Room target = room.get();
                roomList.add(target);

        }

        return roomList;
    }

    /**
     *    회원의 찜 목록 추가
     *   입력해야될 데이터 : writter (작성자), body(내용)
     */
    @Transactional
    public long SaveFavorite(int univid, int roomid, String userid) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FavoriteDto favoriteDto = new FavoriteDto();
        favoriteDto.setUnivid(univid);
        favoriteDto.setRoomid(roomid);
        favoriteDto.setUserId(userid);



        FavoriteDto favoriteDTO = FavoriteDto.builder()
                .id(favoriteDto.getId())
                .userId(favoriteDto.getUserId())
                .roomid(favoriteDto.getRoomid())
                .univid(favoriteDto.getUnivid())
                .build();

        return favoriteRepository.save(favoriteDto.toEntity()).getId(); // 잘모르겠음
    }



    /**
     *   찜 삭제하기
     *   찜 postid, univid 에 접근하여 삭제
     */
    @Transactional
    public void DeleteFavorite(int univid, int roomid,String userid) {
        Optional<Favorite> favoriteWrapper = favoriteRepository.findById((long)roomid);
        Favorite favorite = favoriteWrapper.get();

        FavoriteDto favoriteDTO = FavoriteDto.builder()
                .id(favorite.getId())
                .userId(favorite.getUserId())
                .univid(favorite.getUnivid())
                .roomid(favorite.getRoomid())
                .build();
        favoriteRepository.save(favoriteDTO.toEntity()).getId();
        favoriteRepository.deleteByUnividAndRoomid(univid,roomid);
    }
}
