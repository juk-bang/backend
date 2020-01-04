package com.example.demo.dto;

import com.example.demo.model.Community;
import com.example.demo.model.RecommandFilter;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RecommandFilterDto {
    private long id;
    private double price;
    private double scale;
    private double structure ;
    private double floor;
    private double distance;
    private double grade;

    public RecommandFilter toEntity(){
        RecommandFilter build = RecommandFilter.builder()
                .id(id)
                .price(price)
                .scale(scale)
                .structure(structure)
                .floor(floor)
                .distance(distance)
                .grade(grade)
                .build();
        return build;
    }

    @Builder
    public RecommandFilterDto(long id, double price, double scale, double structure, double floor, double distance, double grade){
        this.id = id;
        this.price = price;
        this.scale = scale;
        this.structure = structure;
        this.floor = floor;
        this.distance = distance;
        this.grade = grade;
    }
}
