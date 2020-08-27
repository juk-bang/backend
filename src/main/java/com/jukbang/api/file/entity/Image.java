package com.jukbang.api.file.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private int roomid;
    @Column(nullable = false)
    private int imageid;
    @Column(length = 30, nullable = false)
    private String filename;

}
