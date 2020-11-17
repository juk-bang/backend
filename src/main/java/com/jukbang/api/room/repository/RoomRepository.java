package com.jukbang.api.room.repository;

import com.jukbang.api.room.dto.LandlordDto;
import com.jukbang.api.room.dto.RoomsDto;
import com.jukbang.api.room.entity.Room;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 방 정보 조회를 위한 레포지터리(Query를 직접 짯음)
 *
 * @author always0ne
 * @version 1.0
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
    /**
     * 주어진 방이 있는지 확인
     *
     * @param roomId   방 ID
     * @param sellerId 집주인 ID
     * @return 주어진 방이 있는지 확인
     */
    boolean existsByRoomIdAndSeller_UserId(Long roomId, String sellerId);

    /**
     * 방 리스트를 필터링 하여 조회
     * 쿼리문을 직접 작성하여 univId 를 제외하고 Nullable하다.
     *
     * @param univId        대학교 ID
     * @param layout        방 구조
     * @param floor         층
     * @param scale         방 넓이
     * @param monthlyLease  월세
     * @param adminExpenses 관리비
     * @param deposit       보증금
     * @param grade         평점
     * @param distance      학교와의 거리
     * @param pageable      페이징 정보
     * @return 방 리스트
     */
    @Query(value = "SELECT new com.jukbang.api.room.dto.RoomsDto(room0.roomId, " +
            "room0.roomInfo.roomName, room0.roomInfo.scale,room0.roomInfo.floor, room0.roomInfo.layout ," +
            "room0.price.monthlyLease, room0.price.adminExpenses,room0.price.deposit," +
            "room0.location.address, room0.location.lat, room0.location.lng," +
            "room0.grade , " +
            "room0.distance)" +
            " FROM Room room0 WHERE room0.univId = :univId" +
            " and (:layout is null or room0.roomInfo.layout = :layout)" +
            " and (:floor is null or room0.roomInfo.floor = :floor)" +
            " and (:scale is null or room0.roomInfo.scale = :scale)" +
            " and (:monthlyLease is null or room0.price.monthlyLease = :monthlyLease)" +
            " and (:adminExpenses is null or room0.price.adminExpenses = :adminExpenses)" +
            " and (:deposit is null or room0.price.deposit = :deposit)" +
            " and (:grade is null or room0.grade = :grade)" +
            " and (:distance is null or room0.distance = :distance)" +
            " and room0.permission = 1"
    )
    Page<RoomsDto> findAllByUnivIdWithFilter(
            @Param("univId") Long univId,
            @Param("layout") Integer layout,
            @Param("floor") Double floor,
            @Param("scale") Double scale,
            @Param("monthlyLease") Double monthlyLease,
            @Param("adminExpenses") Double adminExpenses,
            @Param("deposit") Double deposit,
            @Param("grade") Double grade,
            @Param("distance") Double distance,
            Pageable pageable
    );

    /**
     * 집주인이 올린 매물들 리스트 조회
     *
     * @param sellerId 집주인 ID
     * @param pageable 페이징 정보
     * @return 집주인이 올린 매물들
     */
    @Query(value = "SELECT new com.jukbang.api.room.dto.LandlordDto(room.roomId, room.location.address, room.roomInfo.roomName, room.modifiedDate)" +
            "FROM Room room WHERE room.seller.userId = :sellerId",
            countQuery = "SELECT count(roomId) FROM Room WHERE seller.userId = :sellerId")
    Page<LandlordDto> findAllBySellerId(@Param("sellerId")String sellerId, Pageable pageable);

    Boolean existsByRoomIdAndSeller_UserId(long roomId, String userId);

    List<Room> findAllByPermission(int permission);
}