package com.jukbang.api.room.entity;

import com.jukbang.api.room.entity.embedded.*;
import com.jukbang.api.user.entity.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomId;
    @Column(nullable = false)
    private long univId;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(nullable = false)
    private double grade;

    @Column(nullable = false)
    private int pictureCount;
    @Column(nullable = false)
    private int permission;

    @Embedded
    @NotNull
    private RoomInformation roomInformation;

    @Embedded
    @NotNull
    private Price price;

    @Embedded
    @NotNull
    private ExtraOption extraOption;

    @Embedded
    @NotNull
    private Location location;

    @Embedded
    @NotNull
    private Facilities facilities;

    @OneToOne
    @JoinColumn(name = "seller_id")
    private User seller;

}
