package com.jukbang.api.room.repository;

import com.jukbang.api.room.dto.LandlordDto;
import com.jukbang.api.room.dto.RoomsDto;
import com.jukbang.api.room.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByRoomIdAndSeller_UserId(Long roomId, String sellerId);
    Page<RoomsDto> findAllByUnivId(Long univId, Pageable pageable);

    Page<LandlordDto> findAllBySeller_UserId(String sellerId, Pageable pageable);
}
