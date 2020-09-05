package com.jukbang.api.room.dto;


import com.jukbang.api.room.entity.embedded.Location;
import com.jukbang.api.room.entity.embedded.Option;
import com.jukbang.api.room.entity.embedded.Price;
import com.jukbang.api.room.entity.embedded.RoomInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomDetailDto {
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
}
