package com.jukbang.api.room.entity;

import com.jukbang.api.common.entity.Time;
import com.jukbang.api.room.entity.embedded.Location;
import com.jukbang.api.room.entity.embedded.Option;
import com.jukbang.api.room.entity.embedded.Price;
import com.jukbang.api.room.entity.embedded.RoomInfo;
import com.jukbang.api.user.entity.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Room extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomId;

    @Embedded
    @NotNull
    private RoomInfo roomInfo;

    @Embedded
    @NotNull
    private Price price;

    @Embedded
    @NotNull
    private Option option;

    @Embedded
    @NotNull
    private Location location;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private long univId;

    @Column(nullable = false)
    private double grade;

    @Column(nullable = false)
    private double distance;

    @Column(nullable = false)
    private int pictureCount;

    @Column(nullable = false)
    private int permission;


    @OneToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    public Room(Long univId, Price price, RoomInfo roomInfo, Option option, Location location, String description, User seller) {
        this.roomInfo = roomInfo;
        this.price = price;
        this.option = option;
        this.location = location;
        this.description = description;
        this.univId = univId;
        this.pictureCount = 0;
        this.permission = 0;
        this.seller = seller;
        setDistance(location.getLat(), location.getLng(), 37.496281, 126.957358); // SSU 기준
        setGrade();
    }

    public void updateRoom(Long univId, Price price, RoomInfo roomInfo, Option option, Location location, String description){
        this.roomInfo = roomInfo;
        this.price = price;
        this.option = option;
        this.location = location;
        this.description = description;
        this.univId = univId;
        setDistance(location.getLat(), location.getLng(), 37.496281, 126.957358); // SSU 기준
        setGrade();
    }

    public void permitRoom(){
        this.permission = 1;
    }
    public void rejectRoom(){
        this.permission = 2;
    }

    private void setGrade(){
        this.grade = 0; // 아직 점수 정책 안나옴
    }
    
    private void setDistance(double lat1, double lon1, double lat2, double lon2) {
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(lon1 - lon2));

        this.distance =  rad2deg(Math.acos(dist)) * 60 * 1.1515 * 1609.344;
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    public void addReviewsGrade(double grade, double reviews) {
        this.grade = ((this.grade * reviews + grade)/(reviews+1));
    }

    public void increasePictureCount(){
        this.pictureCount++;
    }
    public void decreasePictureCount(){
        this.pictureCount--;
    }
}
