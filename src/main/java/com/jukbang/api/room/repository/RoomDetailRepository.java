package com.jukbang.api.room.repository;

import com.jukbang.api.room.entity.RoomDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDetailRepository extends JpaRepository<RoomDetail, Long> {

    List<RoomDetail> findAllBySellerid(Long sellerid);
}
