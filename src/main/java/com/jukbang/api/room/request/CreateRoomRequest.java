package com.jukbang.api.room.request;

import com.jukbang.api.room.dto.ExtraOption;
import com.jukbang.api.room.dto.Facilities;
import com.jukbang.api.room.dto.Location;
import com.jukbang.api.room.dto.RoomInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateRoomRequest {
    long Univid;
    int pictureCount;
    RoomInformation roomInformation;
    ExtraOption extraOption;
    String description;
    Location location;
    Facilities facilities;
}
