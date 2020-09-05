package com.jukbang.api.room.dto;

import com.jukbang.api.room.entity.embedded.Location;
import com.jukbang.api.room.entity.embedded.Price;
import com.jukbang.api.room.entity.embedded.RoomInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@AllArgsConstructor
@Relation(collectionRelation = "rooms")
public class RoomsDto {
    private final long roomId;
    private final RoomInfo roomInfo;
    private final Price price;
    private final Location location;
    private final double grade;
    private final double distance;
}
