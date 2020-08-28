package com.jukbang.api.user.dto;


import com.jukbang.api.user.entity.User;
import lombok.*;

// data to object

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private long id;
    private String userId;
    private int univId;

/*    *//**
     * class -> Entity
     *//*

    public User toEntity() {
        User build = User.builder()
                .id(id)
                .userId(userId)
                .univId(univId)
                .build();
        return build;
    }*/

    /**
     * Entity -> class
     */

    @Builder
    public UserDto(long id, String userId, int univId) {
        this.id = id;
        this.userId = userId;
        this.univId = univId;
    }
}
