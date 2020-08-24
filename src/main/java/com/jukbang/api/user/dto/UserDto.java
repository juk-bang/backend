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
    private int univid;

    /**
     * class -> Entity
     */

    public User toEntity() {
        User build = User.builder()
                .id(id)
                .userId(userId)
                .univid(univid)
                .build();
        return build;
    }

    /**
     * Entity -> class
     */

    @Builder
    public UserDto(long id, String userId, int univid) {
        this.id = id;
        this.userId = userId;
        this.univid = univid;
    }
}
