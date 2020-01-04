package com.example.demo.dto;


import com.example.demo.model.User;
import lombok.*;


import java.time.LocalDateTime;

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
     *  Entity -> class
     */

    @Builder
    public UserDto(long id,String userId,int univid) {
        this.id=id;
        this.userId=userId;
        this.univid=univid;
    }
}
