package com.example.demo.repository;

import com.example.demo.model.Room;
import com.example.demo.model.RoomDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDetailRepository extends JpaRepository<RoomDetail,Long> {

    List<RoomDetail> findAllBySellerid(Long sellerid);
}
