package com.jukbang.api.room.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RoomInformation {
    @Column(length = 100, nullable = false)
    private String address;
    @Column(nullable = false)
    private int structure;
    @Column(nullable = false)
    private double scale;
    @Column(nullable = false)
    private double floor;
    @Column(nullable = false)
    private double distance;
}
