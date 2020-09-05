package com.jukbang.api.room.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LandlordDto {
    private final long roomId;
    private final String address;
    private final String roomName;
    private final LocalDateTime modifiedDate;
}
