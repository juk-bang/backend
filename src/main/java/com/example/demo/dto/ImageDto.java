package com.example.demo.dto;

import com.example.demo.model.Comments;
import com.example.demo.model.Image;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImageDto {

    private long id;
    private int univid;
    private int roomid;
    private int imageid;
    private String filename;
    /**
     * class -> Entity
     */

    public Image toEntity() {
        Image build = Image.builder()
                .id(id)
                .univid(univid)
                .roomid(roomid)
                .imageid(imageid)
                .filename(filename)
                .build();
        return build;
    }

    /**
     *  Entity -> class
     */

    @Builder
    public ImageDto(long id, int univid, int roomid,int imageid, String filename) {
        this.id=id;
        this.univid = univid;
        this.roomid=roomid;
        this.imageid=imageid;
        this.filename=filename;
    }
}
