package com.jukbang.api.dto;


import com.jukbang.api.model.MessageChatRoom;
import lombok.*;

// data to object

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MessageChatRoomDto {
    private long chatRoom;
    private String buyer;
    private String seller;

    /**
     * class -> Entity
     */

    public MessageChatRoom toEntity() {
        MessageChatRoom build = MessageChatRoom.builder()
                .chatRoom(chatRoom)
                .buyer(buyer)
                .seller(seller)
                .build();
        return build;
    }

    /**
     * Entity -> class
     */

    @Builder
    public MessageChatRoomDto(long chatRoom, String buyer, String seller) {
        this.chatRoom = chatRoom;
        this.buyer = buyer;
        this.seller = seller;
    }
}