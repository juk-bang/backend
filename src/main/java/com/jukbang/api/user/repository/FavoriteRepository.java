package com.jukbang.api.user.repository;

import com.jukbang.api.user.dto.FavoriteRoomsDto;
import com.jukbang.api.user.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

  @Query(value = "SELECT new com.jukbang.api.user.dto.FavoriteRoomsDto(room.roomId, " +
      "room.roomInfo.roomName, room.roomInfo.scale,room.roomInfo.floor, room.roomInfo.layout ," +
      "room.price.monthlyLease, room.price.adminExpenses,room.price.deposit," +
      "room.location.address, room.location.lat, room.location.lng," +
      "room.grade , room.distance)" +
      "FROM Favorite WHERE user.userId=:userId")
  List<FavoriteRoomsDto> findAllByUser_UserId(@Param("userId")String userId);

  Optional<Favorite> findByUser_UserIdAndRoom_RoomId(String userId, long roomId);
}
