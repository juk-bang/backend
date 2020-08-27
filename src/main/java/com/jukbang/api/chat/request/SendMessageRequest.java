package com.jukbang.api.chat.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageRequest {
    private long id;
    private String sender;
    private String receiver;
    private String body;
    private long chatRoom;
    private int isRead;
    private LocalDateTime modifiedDate;
}
