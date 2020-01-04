package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GetRoomDetailWrapper {
     long Univid;
     int pictureCount;
     RoomInformation roomInformation;
    ExtraOption extraOption;
     String description;
     Location location;
     Facilities facilities;




    @Builder
    public GetRoomDetailWrapper(int pictureCount, String address, int structure, double scale, double floor, double month, double adminExpenses, double deposit, boolean elevator, boolean park, boolean cctv, boolean autoDoor, boolean washingMachine, boolean gasrange, boolean refrigerator, boolean airconditioner, String description, double lat, double lng, boolean busStation, boolean subwayStation){

        this.pictureCount = pictureCount;
        this.roomInformation.address = address;
        this.roomInformation.structure = structure;
        this.roomInformation.scale = scale;
        this.roomInformation.floor = floor;
        this.roomInformation.price.month = month;
        this.roomInformation.price.adminExpenses = adminExpenses;
        this.roomInformation.price.deposit = deposit;
        this.extraOption.elevator = elevator;
        this.extraOption.park = park;
        this.extraOption.cctv = cctv;
        this.extraOption.autoDoor = autoDoor;
        this.extraOption.washingMachine = washingMachine;
        this.extraOption.gasrange = gasrange;
        this.extraOption.refrigerator = refrigerator;
        this.extraOption.airconditioner = airconditioner;
        this.description = description;
        this.location.lat = lat;
        this.location.lng = lng;
        this.facilities.busStation = busStation;
        this.facilities.subwayStation =subwayStation;
    }
}

