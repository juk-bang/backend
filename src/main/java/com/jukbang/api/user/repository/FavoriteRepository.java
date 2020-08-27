package com.jukbang.api.user.repository;

import com.jukbang.api.user.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findAllByUserId(String userid);

    Optional<Favorite> findByRoomid(int roomId);

    List<Favorite> deleteByUnividAndRoomid(int univid, int roomid);

}
