package com.jukbang.api.room.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

/**
 * 집주인이 올린 매물들 리스트에 사용되는 데이터 전송객체
 *
 * @author always0ne
 * @version 1.0
 */
@Getter
@AllArgsConstructor
@Relation(collectionRelation = "rooms")
public class LandlordDto {
    /**
     * 방 ID
     */
    private final Long roomId;
    /**
     * 주소
     */
    private final String address;
    /**
     * 방 이름
     */
    private final String roomName;
    /**
     * 수정일
     */
    private final LocalDateTime modifiedDate;
}
