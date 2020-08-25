package com.jukbang.api.chat.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SendMessageRequest {
    private long id;
    private String sender;
    private String receiver;
    private String body;
    private long chatRoom;
    private int isRead;
    private LocalDateTime modifiedDate;
}
