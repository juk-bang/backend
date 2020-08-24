package com.jukbang.api.chat.entity;

import com.jukbang.api.common.entity.Time;
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

public class MessageChatRoom extends Time {
    @Id
    /**
     *  각 댓글의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chatRoom;


    /**
     * 쪽지 user1
     */
    @Column(length = 30, nullable = false)
    private String buyer;

    /**
     * 쪽지를 user2
     */
    @Column(length = 30, nullable = false)
    private String seller;


    @Builder
    public MessageChatRoom(long chatRoom, String buyer, String seller) {
        this.chatRoom = chatRoom;
        this.buyer = buyer;
        this.seller = seller;

    }
}
