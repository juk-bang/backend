package com.jukbang.api.chat.request;

import java.time.LocalDateTime;

public class UpdateChatRoomRequest {
    private long id;
    private String sender;
    private String receiver;
    private String body;
    private long chatRoom;
    private int isRead;
    private LocalDateTime modifiedDate;
}
