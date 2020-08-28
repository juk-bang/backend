package com.jukbang.api.room.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomDetailDto {
    long id;
    int pictureCount;
    String sellerId;
    String address;
    boolean elevator;
    boolean park;
    boolean cctv;
    boolean autoDoor;
    boolean washingMachine;
    boolean gasrange;
    boolean refrigerator;
    boolean airconditioner;
    boolean busStation;
    boolean subwayStation;
    String description;

    @Builder
    public RoomDetailDto(long id, int pictureCount, String sellerId, String address, boolean elevator, boolean park, boolean cctv, boolean autoDoor, boolean washingMachine, boolean gasrange, boolean refrigerator, boolean airconditioner, boolean busStation, boolean subwayStation, String description) {
        this.id = id;
        this.pictureCount = pictureCount;
        this.sellerId = sellerId;
        this.address = address;
        this.elevator = elevator;
        this.park = park;
        this.cctv = cctv;
        this.autoDoor = autoDoor;
        this.washingMachine = washingMachine;
        this.gasrange = gasrange;
        this.refrigerator = refrigerator;
        this.airconditioner = airconditioner;
        this.busStation = busStation;
        this.subwayStation = subwayStation;
        this.description = description;
    }
}
