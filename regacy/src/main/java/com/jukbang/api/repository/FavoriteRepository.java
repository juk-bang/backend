package com.jukbang.api.repository;

import com.jukbang.api.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findAllByUserId(String userid);

    List<Favorite> deleteByUnividAndRoomid(int univid, int roomid);

}
