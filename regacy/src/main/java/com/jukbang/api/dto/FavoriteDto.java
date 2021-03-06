package com.jukbang.api.dto;


import com.jukbang.api.model.Favorite;
import lombok.*;

// data to object

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FavoriteDto {
    private long id;
    private String userId;
    private int univid;
    private int roomid;

    /**
     * class -> Entity
     */

    public Favorite toEntity() {
        Favorite build = Favorite.builder()
                .id(id)
                .userId(userId)
                .univid(univid)
                .roomid(roomid)
                .build();
        return build;
    }

    /**
     * Entity -> class
     */

    @Builder
    public FavoriteDto(long id, String userId, int univid, int roomid) {
        this.id = id;
        this.userId = userId;
        this.univid = univid;
        this.roomid = roomid;
    }
}