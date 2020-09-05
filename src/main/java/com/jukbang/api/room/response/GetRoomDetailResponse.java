package com.jukbang.api.room.response;

import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.entity.embedded.Location;
import com.jukbang.api.room.entity.embedded.Option;
import com.jukbang.api.room.entity.embedded.Price;
import com.jukbang.api.room.entity.embedded.RoomInfo;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class GetRoomDetailResponse {
    private long roomId;
    private RoomInfo roomInfo;
    private Price price;
    private Option option;
    private Location location;
    private String description;
    private long univId;
    private double grade;
    private double distance;
    private int pictureCount;
    private String sellerId;

    public GetRoomDetailResponse(Room room){
        this.roomId = room.getRoomId();
        this.roomInfo = room.getRoomInfo();
        this.price = room.getPrice();
        this.option = room.getOption();
        this.location = room.getLocation();
        this.description = room.getDescription();
        this.univId = room.getUnivId();
        this.grade = room.getGrade();
        this.distance = room.getDistance();
        this.pictureCount = room.getPictureCount();
        this.sellerId = room.getSeller().getUserId();
    }
}
