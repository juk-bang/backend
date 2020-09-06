package com.jukbang.api.room.dto;

import com.jukbang.api.room.entity.embedded.Location;
import com.jukbang.api.room.entity.embedded.Price;
import com.jukbang.api.room.entity.embedded.RoomInfo;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

/**
 * 방 리스트에 사용되는 데이터 전송객체
 * Projection을 위해 생성자가 튜닝됨
 *
 * @author always0ne
 * @version 1.0
 */
@Getter
@Relation(collectionRelation = "rooms")
public class RoomsDto {
    /**
     * 방 ID
     */
    private final Long roomId;
    /**
     * 방 정보
     */
    private final RoomInfo roomInfo;
    /**
     * 방 가격정보
     */
    private final Price price;
    /**
     * 방 위치정보
     */
    private final Location location;
    /**
     * 방 점수
     */
    private final Double grade;
    /**
     * 학교와의 거리
     */
    private final Double distance;

    public RoomsDto(Long roomId,
                    String roomName, Double scale, Double floor, Integer layout,
                    Double monthlyLease, Double adminExpenses, Double deposit,
                    String address, Double lat, Double lng,
                    Double grade, Double distance) {
        this.roomId = roomId;
        this.roomInfo = new RoomInfo(roomName, scale, floor, layout);
        this.price = new Price(monthlyLease, adminExpenses, deposit);
        this.location = new Location(address, lat, lng);
        this.grade = grade;
        this.distance = distance;
    }
}
