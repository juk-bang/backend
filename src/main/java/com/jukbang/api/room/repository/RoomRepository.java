package com.jukbang.api.room.repository;

import com.jukbang.api.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByUnivid(Long Univid);

    List<Room> findAllByUnividAndPermission(Long Univid, int permission);
}
