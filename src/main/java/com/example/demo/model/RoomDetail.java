package com.example.demo.model;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table
public class RoomDetail {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false)
    private int pictureCount;
    @Column(length = 100, nullable =false)
    private String sellerid;
    @Column(length = 100, nullable =false)
    private String address;
    @Column(nullable=false)
    private boolean elevator;
    @Column(nullable=false)
    private boolean park;
    @Column(nullable=false)
    private boolean cctv;
    @Column(nullable=false)
    private boolean autoDoor;
    @Column(nullable=false)
    private boolean washingMachine;
    @Column(nullable=false)
    private boolean gasrange;
    @Column(nullable=false)
    private boolean refrigerator;
    @Column(nullable=false)
    private boolean airconditioner;
    @Column(nullable=false)
    private boolean busStation;
    @Column(nullable=false)
    private boolean subwayStation;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String description;
    @Builder
    public RoomDetail(long id, int pictureCount, String sellerid, String address, boolean elevator, boolean park, boolean cctv, boolean autoDoor, boolean washingMachine, boolean gasrange, boolean refrigerator, boolean airconditioner, boolean busStation, boolean subwayStation, String description){
        this.id = id;
        this.pictureCount =pictureCount;
        this.sellerid = sellerid;
        this.address = address;
        this.elevator = elevator;
        this.park = park;
        this.cctv = cctv;
        this.autoDoor = autoDoor;
        this.washingMachine = washingMachine;
        this.gasrange = gasrange;
        this.refrigerator =refrigerator;
        this.airconditioner =airconditioner;
        this.busStation = busStation;
        this.subwayStation =subwayStation;
        this.description = description;
    }
}
