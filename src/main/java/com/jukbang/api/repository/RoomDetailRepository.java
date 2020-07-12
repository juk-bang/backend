package com.jukbang.api.repository;

import com.jukbang.api.model.RoomDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDetailRepository extends JpaRepository<RoomDetail, Long> {

    List<RoomDetail> findAllBySellerid(Long sellerid);
}
