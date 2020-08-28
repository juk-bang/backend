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
public class ExtraOption {
    @Column(nullable = false)
    private boolean elevator;
    @Column(nullable = false)
    private boolean park;
    @Column(nullable = false)
    private boolean cctv;
    @Column(nullable = false)
    private boolean autoDoor;
    @Column(nullable = false)
    private boolean washingMachine;
    @Column(nullable = false)
    private boolean gasrange;
    @Column(nullable = false)
    private boolean refrigerator;
    @Column(nullable = false)
    private boolean airconditioner;
}
