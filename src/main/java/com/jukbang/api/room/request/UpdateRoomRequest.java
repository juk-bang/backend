package com.jukbang.api.room.request;

import com.jukbang.api.room.dto.ExtraOption;
import com.jukbang.api.room.dto.Facilities;
import com.jukbang.api.room.dto.Location;
import com.jukbang.api.room.dto.RoomInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UpdateRoomRequest {
    private long univId;
    private int pictureCount;
    private RoomInformation roomInformation;
    private ExtraOption extraOption;
    private String description;
    private Location location;
    private Facilities facilities;
}
