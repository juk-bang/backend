package com.jukbang.api.room.request;

import com.jukbang.api.room.dto.ExtraOption;
import com.jukbang.api.room.dto.Facilities;
import com.jukbang.api.room.dto.Location;
import com.jukbang.api.room.dto.RoomInformation;

public class UpdateRoomRequest {
    long Univid;
    int pictureCount;
    RoomInformation roomInformation;
    ExtraOption extraOption;
    String description;
    Location location;
    Facilities facilities;
}
