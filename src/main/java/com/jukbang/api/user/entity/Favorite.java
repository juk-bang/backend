package com.jukbang.api.user.entity;

import com.jukbang.api.room.entity.Room;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Favorite {
    @Id
    /**
     *  각 댓글의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 해당 회원의 찜한 방
     */
    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private User user;

    public Favorite(Room room, User user){
        this.room =room;
        this.user = user;
    }
}
