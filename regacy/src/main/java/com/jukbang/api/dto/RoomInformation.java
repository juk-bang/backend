package com.jukbang.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomInformation {
    String address;
    int structure;
    double scale;
    double floor;
    Price price;

}
