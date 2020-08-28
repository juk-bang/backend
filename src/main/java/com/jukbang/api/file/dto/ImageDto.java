package com.jukbang.api.file.dto;

import com.jukbang.api.file.entity.Image;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImageDto {

    private long id;
    private int roomid;
    private int imageid;
    private String filename;

    /**
     * class -> Entity
     */

    public Image toEntity() {
        Image build = Image.builder()
                .id(id)
                .roomId(roomid)
                .imageId(imageid)
                .filename(filename)
                .build();
        return build;
    }

    /**
     * Entity -> class
     */

    @Builder
    public ImageDto(long id,  int roomid, int imageid, String filename) {
        this.id = id;
        this.roomid = roomid;
        this.imageid = imageid;
        this.filename = filename;
    }
}
