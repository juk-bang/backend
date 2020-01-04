package com.example.demo.dto;

import com.example.demo.model.RecommandFilter;
import com.example.demo.model.Room;
import com.example.demo.model.RoomDetail;
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

    public Room toEntity(){
        Room build = Room.builder()
                .id(id)
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
                .build();
        return build;
    }

    @Builder
    public RoomDto(long id, int structure, double month, double adminExpenses, double deposit, double floor, double scale,double grade, double distance, double lat, double lng,long univid){
        this.id = id;
        this.structure = structure;
        this.month = month;
        this.adminExpenses = adminExpenses;
        this.deposit = deposit;
        this.floor = floor;
        this.scale = scale;
        this.grade = grade;
        this.distance = distance;
        this.lat=lat;
        this.lng=lng;
        this.univid = univid;
    }
}
