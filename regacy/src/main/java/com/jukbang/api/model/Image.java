package com.jukbang.api.model;

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
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int univid;
    @Column(nullable = false)
    private int roomid;
    @Column(nullable = false)
    private int imageid;
    @Column(length = 30, nullable = false)
    private String filename;

    @Builder
    public Image(long id, int univid, int roomid, int imageid, String filename) {
        this.id = id;
        this.univid = univid;
        this.roomid = roomid;
        this.imageid = imageid;
        this.filename = filename;
    }
}
