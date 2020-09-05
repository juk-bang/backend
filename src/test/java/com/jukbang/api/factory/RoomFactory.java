package com.jukbang.api.factory;

import com.jukbang.api.room.entity.embedded.Location;
import com.jukbang.api.room.entity.embedded.Option;
import com.jukbang.api.room.entity.embedded.Price;
import com.jukbang.api.room.entity.embedded.RoomInfo;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.request.UpdateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomFactory {
    @Autowired
    protected RoomService roomService;

    public Long generateRoom(String sellerId) {
        return roomService.registerRoom(sellerId, generateCreateRoomRequest());
    }

    public CreateRoomRequest generateCreateRoomRequest() {
        return CreateRoomRequest.builder()
                .description("설명")
                .option(new Option(true, true, true, true, true, true, true, true))
                .location(new Location("Test Location",123, 412))
                .roomInfo(new RoomInfo("주소", 1, 1, 1))
                .price(new Price(1, 1, 1))
                .univId(1)
                .build();
    }

    public UpdateRoomRequest generateUpdateRoomRequest() {
        return UpdateRoomRequest.builder()
                .description("설명")
                .option(new Option(true, true, true, true, true, true, true, true))
                .location(new Location("Test Location",123, 412))
                .roomInfo(new RoomInfo("주소", 1, 1, 1))
                .price(new Price(1, 1, 1))
                .univId(1)
                .build();
    }

}
