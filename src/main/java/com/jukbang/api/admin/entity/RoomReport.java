package com.jukbang.api.admin.entity;

import com.jukbang.api.room.entity.Room;
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
public class RoomReport {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private long roomReportId;

    @Column(nullable = false)
    private long roomid;

    @Column(nullable = false)
    private int type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String detail;

    public RoomReport( long roomId, int type, String detail){
        this.roomid = roomId;
        this.type = type;
        this.detail = detail;
    }

}
