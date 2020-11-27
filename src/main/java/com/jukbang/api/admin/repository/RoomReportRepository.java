package com.jukbang.api.admin.repository;

import com.jukbang.api.admin.entity.RoomReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomReportRepository extends JpaRepository<RoomReport, Long> {
    Optional<RoomReport> findByRoomIdAndRoomReportId(Long roomId, Long roomReportId);
}
