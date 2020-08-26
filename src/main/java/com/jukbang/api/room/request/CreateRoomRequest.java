package com.jukbang.api.room.request;

import com.jukbang.api.room.dto.ExtraOption;
import com.jukbang.api.room.dto.Facilities;
import com.jukbang.api.room.dto.Location;
import com.jukbang.api.room.dto.RoomInformation;
import lombok.Getter;

@Getter
public class CreateRoomRequest {
    long Univid;
    int pictureCount;
    RoomInformation roomInformation;
    ExtraOption extraOption;
    String description;
    Location location;
    Facilities facilities;
}
