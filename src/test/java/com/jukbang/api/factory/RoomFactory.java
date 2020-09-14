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
                .location(new Location("서울 동작구 사당로 36-1 서정캐슬 3층",37.494863, 126.958453))
                .roomInfo(new RoomInfo("투썸 플레이스 3층", 1, 1, 1))
                .price(new Price(1, 1, 1))
                .univId(1)
                .build();
    }

    public UpdateRoomRequest generateUpdateRoomRequest() {
        return UpdateRoomRequest.builder()
                .description("설명")
                .option(new Option(true, true, true, true, true, true, true, true))
                .location(new Location("서울특별시 동작구 상도동 504",37.494601, 126.957527))
                .roomInfo(new RoomInfo("상쾌한 방", 1, 1, 1))
                .price(new Price(1, 1, 1))
                .univId(1)
                .build();
    }

}
