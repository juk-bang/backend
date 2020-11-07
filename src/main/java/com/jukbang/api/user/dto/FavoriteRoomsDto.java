package com.jukbang.api.user.dto;

import com.jukbang.api.room.entity.embedded.Price;
import com.jukbang.api.room.entity.embedded.RoomInfo;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "rooms")
public class FavoriteRoomsDto {
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
   * 방 점수
   */
  private final Double grade;
  /**
   * 학교와의 거리
   */
  private final Double distance;

  public FavoriteRoomsDto(Long roomId,
                          String roomName, Double scale, Double floor, Integer layout,
                          Double monthlyLease, Double adminExpenses, Double deposit,
                          Double grade, Double distance) {
    this.roomId = roomId;
    this.roomInfo = new RoomInfo(roomName, scale, floor, layout);
    this.price = new Price(monthlyLease, adminExpenses, deposit);
    this.grade = grade;
    this.distance = distance;
  }
}
