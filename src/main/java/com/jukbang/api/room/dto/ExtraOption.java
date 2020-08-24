package com.jukbang.api.room.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExtraOption {
    boolean elevator;
    boolean park;
    boolean cctv;
    boolean autoDoor;
    boolean washingMachine;
    boolean gasrange;
    boolean refrigerator;
    boolean airconditioner;

}
