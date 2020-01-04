package com.example.demo.dto;


import com.example.demo.model.RoomDetail;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomDetailDto {
    long id;
    int pictureCount;
    long sellerid;
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

    public RoomDetail toEntity() {
        RoomDetail build = RoomDetail.builder()
                .id(id)
                .pictureCount(pictureCount)
                .sellerid(sellerid)
                .address(address)
                .elevator(elevator)
                .park(park)
                .cctv(cctv)
                .autoDoor(autoDoor)
                .washingMachine(washingMachine)
                .gasrange(gasrange)
                .refrigerator(refrigerator)
                .airconditioner(airconditioner)
                .busStation(busStation)
                .subwayStation(subwayStation)
                .description(description)
                .build();
        return build;
    }

    @Builder
    public RoomDetailDto(long id, int pictureCount, long sellerid, String address, boolean elevator, boolean park, boolean cctv, boolean autoDoor, boolean washingMachine, boolean gasrange, boolean refrigerator, boolean airconditioner, boolean busStation, boolean subwayStation, String description) {
        this.id = id;
        this.pictureCount = pictureCount;
        this.sellerid = sellerid;
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
