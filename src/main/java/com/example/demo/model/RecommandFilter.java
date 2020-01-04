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
public class RecommandFilter {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false)
    private double price;
    @Column(nullable=false)
    private double scale;
    @Column(nullable=false)
    private double structure ;
    @Column(nullable=false)
    private double floor;
    @Column(nullable=false)
    private double distance;
    @Column(nullable=false)
    private double grade;
    @Column(nullable=false)
    private long univid;

    @Builder
    public RecommandFilter(long id, double price, double scale, double structure, double floor, double distance, double grade,long univid){
        this.id = id;
        this.price = price;
        this.scale = scale;
        this.structure = structure;
        this.floor = floor;
        this.distance = distance;
        this.grade = grade;
        this.univid = univid;
    }
}
