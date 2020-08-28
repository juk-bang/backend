package com.jukbang.api.room.dto;

import com.jukbang.api.room.entity.Room;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomDto {
    private long id;
    private int structure;
    private double month;
    private double adminExpenses;
    private double deposit;
    private double floor;
    private double scale;
    private double grade;
    private double distance;
    private double lat;
    private double lng;
    private long univid;
    private int permission;

/*    public Room toEntity() {
        Room build = Room.builder()
                .roomId(id)
                .structure(structure)
                .month(month)
                .adminExpenses(adminExpenses)
                .deposit(deposit)
                .floor(floor)
                .scale(scale)
                .grade(grade)
                .distance(distance)
                .lat(lat)
                .lng(lng)
                .univid(univid)
                .permission(permission)
                .build();
        return build;
    }*/

    @Builder
    public RoomDto(long id, int structure, double month, double adminExpenses, double deposit, double floor, double scale, double grade, double distance, double lat, double lng, long univid, int permission) {
        this.id = id;
        this.structure = structure;
        this.month = month;
        this.adminExpenses = adminExpenses;
        this.deposit = deposit;
        this.floor = floor;
        this.scale = scale;
        this.grade = grade;
        this.distance = distance;
        this.lat = lat;
        this.lng = lng;
        this.univid = univid;
        this.permission = permission;
    }
}
