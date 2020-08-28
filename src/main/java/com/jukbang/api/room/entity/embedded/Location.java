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
public class Location {
    @Column(nullable = false)
    private boolean busStation;
    @Column(nullable = false)
    private boolean subwayStation;
}
