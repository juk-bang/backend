package com.jukbang.api.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomlistWrapper {
    private List<RoomDto> room;
    private RecommandFilterDto recommandFilter;


    @Builder
    public RoomlistWrapper(List<RoomDto> room, RecommandFilterDto recommandFilterDto) {
        this.room = room;
        this.recommandFilter = recommandFilterDto;
    }


}
