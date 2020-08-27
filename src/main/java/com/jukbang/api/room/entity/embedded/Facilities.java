package com.jukbang.api.room.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Facilities {
    @Column(nullable = false)
    private double lat;
    @Column(nullable = false)
    private double lng;
}
