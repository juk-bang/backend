package com.jukbang.api.user.service;

import com.jukbang.api.room.exception.RoomNotFoundException;
import com.jukbang.api.room.repository.RoomRepository;
import com.jukbang.api.user.dto.FavoriteRoomsDto;
import com.jukbang.api.user.entity.Favorite;
import com.jukbang.api.user.exception.FavoriteAlreadyExistsException;
import com.jukbang.api.user.exception.FavoriteNotFoundException;
import com.jukbang.api.user.exception.UserNotFoundException;
import com.jukbang.api.user.repository.FavoriteRepository;
import com.jukbang.api.user.repository.UserRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteService {
  private final FavoriteRepository favoriteRepository;
  private final RoomRepository roomRepository;
  private final UserRepository userRepository;

  /**
   * 회원의 찜 목록 출력
   */
  @Transactional
  public List<FavoriteRoomsDto> getFavoriteList(String userId) {
    return favoriteRepository.findAllByUser_UserId(userId);
  }

  /**
   * 회원의 찜 목록 추가
   */
  @Transactional
  public long SaveFavorite(long roomId, String userId) {
    if(favoriteRepository.existsByUser_UserIdAndRoom_RoomId(userId,roomId))
      throw new FavoriteAlreadyExistsException();
    return favoriteRepository
        .save(new Favorite(roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new),
            userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new))).getId();
  }

  /**
   * 찜 삭제하기
   * 찜 postid, univid 에 접근하여 삭제
   */
  @Transactional
  public void DeleteFavorite(long roomId, String userId) {
    Favorite favorite = favoriteRepository.findByUser_UserIdAndRoom_RoomId(userId, roomId)
        .orElseThrow(FavoriteNotFoundException::new);
    favoriteRepository.delete(favorite);
  }
}
