package com.jukbang.api.user.service;

import com.jukbang.api.room.dto.FavoriteDto;
import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.repository.RoomRepository;
import com.jukbang.api.user.entity.Favorite;
import com.jukbang.api.user.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final RoomRepository roomRepository;

    /**
     * 회원의 찜 목록 출력
     */
    @Transactional
    public List<Room> getFavoriteList(String userid) {
/*        List<Favorite> boardEntities = favoriteRepository.findAllByUserId(userid);
        List<Room> roomList = new ArrayList<>();
        for (Favorite boardEntity : boardEntities) {
            Optional<Room> room = roomRepository.findById((long) boardEntity.getRoomid());
            Room target = room.get();
            roomList.add(target);

        }

        return roomList;*/
        return null;
    }

    /**
     * 회원의 찜 목록 추가
     * 입력해야될 데이터 : writter (작성자), body(내용)
     */
    @Transactional
    public long SaveFavorite(int univid, int roomid, String userid) {
/*        FavoriteDto favoriteDto = new FavoriteDto();
        favoriteDto.setUnivid(univid);
        favoriteDto.setRoomid(roomid);
        favoriteDto.setUserId(userid);

        FavoriteDto favoriteDTO = FavoriteDto.builder()
                .id(favoriteDto.getId())
                .userId(favoriteDto.getUserId())
                .roomid(favoriteDto.getRoomid())
                .univid(favoriteDto.getUnivid())
                .build();

        return favoriteRepository.save(favoriteDto.toEntity()).getId(); // 잘모르겠음*/
        return 0;
    }

    /**
     * 찜 삭제하기
     * 찜 postid, univid 에 접근하여 삭제
     */
    @Transactional
    public void DeleteFavorite(int univid, int roomid, String userid) {
/*        Optional<Favorite> favoriteWrapper = favoriteRepository.findByRoomid(roomid);
        Favorite favorite = favoriteWrapper.get();

        FavoriteDto favoriteDTO = FavoriteDto.builder()
                .id(favorite.getId())
                .roomid(favorite.getRoomid())
                .build();
        favoriteRepository.save(favoriteDTO.toEntity()).getId();
        favoriteRepository.deleteByUnividAndRoomid(univid, roomid);*/
    }
}
