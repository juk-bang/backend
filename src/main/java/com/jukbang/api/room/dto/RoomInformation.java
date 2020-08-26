package com.jukbang.api.room.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomInformation {
    String address;
    int structure;
    double scale;
    double floor;
    Price price;

}
