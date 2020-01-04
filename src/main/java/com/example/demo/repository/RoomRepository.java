package com.example.demo.repository;

import com.example.demo.model.Comments;
import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findAllByUnivid(Long Univid);
    List<Room> findAllByUnividAndPermission(Long Univid, int permission);
}
