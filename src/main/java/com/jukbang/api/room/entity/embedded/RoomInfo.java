package com.jukbang.api.room.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RoomInfo {

    @Column(length = 100, nullable = false)
    private String roomName;
    @Column(nullable = false)
    private double scale;
    @Column(nullable = false)
    private double floor;
    @Column(nullable = false)
    private int layout;
}
