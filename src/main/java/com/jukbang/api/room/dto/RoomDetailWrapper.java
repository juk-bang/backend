package com.jukbang.api.room.dto;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomDetailWrapper {
    private long id;
    private int pictureCount;
    private String sellerId;
    private String address;
    private int structure;
    private double scale;
    private double floor;
    private double distance;
    private double month;
    private double adminExpenses;
    private double deposit;
    private double grade;
    private boolean elevator;
    private boolean park;
    private boolean cctv;
    private boolean autoDoor;
    private boolean washingMachine;
    private boolean gasrange;
    private boolean refrigerator;
    private boolean airconditioner;
    String description;
    private double lat;
    private double lng;
    private boolean busStation;
    private boolean subwayStation;


    @Builder
    public RoomDetailWrapper(long id, int pictureCount, String sellerId, String address, int structure, double scale, double floor, double distance, double month, double adminExpenses, double deposit, double grade, boolean elevator, boolean park, boolean cctv, boolean autoDoor, boolean washingMachine, boolean gasrange, boolean refrigerator, boolean airconditioner, String description, double lat, double lng, boolean busStation, boolean subwayStation) {

        this.id = id;
        this.pictureCount = pictureCount;
        this.sellerId = sellerId;
        this.address = address;
        this.structure = structure;
        this.scale = scale;
        this.floor = floor;
        this.distance = distance;
        this.month = month;
        this.adminExpenses = adminExpenses;
        this.deposit = deposit;
        this.grade = grade;
        this.elevator = elevator;
        this.park = park;
        this.cctv = cctv;
        this.autoDoor = autoDoor;
        this.washingMachine = washingMachine;
        this.gasrange = gasrange;
        this.refrigerator = refrigerator;
        this.airconditioner = airconditioner;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        this.busStation = busStation;
        this.subwayStation = subwayStation;
    }
}
