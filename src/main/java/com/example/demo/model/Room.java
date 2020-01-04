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
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false)
    private int structure;
    @Column(nullable=false)
    private double month;
    @Column(nullable=false)
    private double adminExpenses ;
    @Column(nullable=false)
    private double deposit;
    @Column(nullable=false)
    private double floor;
    @Column(nullable=false)
    private double scale;
    @Column(nullable=false)
    private double distance;
    @Column(nullable=false)
    private double lat;
    @Column(nullable=false)
    private double lng;
    @Column(nullable=false)
    private long univid;
    @Column(nullable=false)
    private double grade;

    @Builder
    public Room(long id, int structure, double month, double adminExpenses, double deposit, double floor, double scale, double distance, double lat, double lng,long univid,double grade){
        this.id = id;
        this.structure = structure;
        this.month = month;
        this.adminExpenses = adminExpenses;
        this.deposit = deposit;
        this.floor = floor;
        this.scale = scale;
        this.distance = distance;
        this.grade = grade;
        this.lat=lat;
        this.lng=lng;
        this.univid =univid;
    }
}
