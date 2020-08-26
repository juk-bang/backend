package com.jukbang.api.factory;

import com.jukbang.api.room.dto.*;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomFactory {
    @Autowired
    protected RoomService roomService;

    public Long generateRoom(String sellerId) {
        return roomService.createRoom(sellerId, generateCreateRoomRequest());
    }

    public CreateRoomRequest generateCreateRoomRequest() {
        return CreateRoomRequest.builder()
                .description("설명")
                .extraOption(new ExtraOption(true, true, true, true, true, true, true, true))
                .facilities(new Facilities(true, true))
                .location(new Location(123, 412))
                .pictureCount(0)
                .roomInformation(new RoomInformation("주소", 1, 1, 1, new Price(1, 1, 1)))
                .Univid((long) 1)
                .build();
    }

}
