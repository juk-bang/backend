package com.jukbang.api.chat.request;

import lombok.Getter;

@Getter
public class CreateChatRoomRequest {
    private long chatRoom;
    private String buyer;
    private String seller;
}
