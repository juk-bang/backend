package com.jukbang.api.file.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
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
    private int roomId;
    @Column(nullable = false)
    private int imageId;
    @Column(length = 30, nullable = false)
    private String filename;

}
