package com.example.demo.dto;


import com.example.demo.model.Message;
import lombok.*;


import java.time.LocalDateTime;

// data to object

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MessageDto {
    private long id;
    private String sender;
    private String receiver;
    private String body;
    private long chatRoom;
    private int isRead;
    private LocalDateTime modifiedDate;


    /**
     * class -> Entity
     */

    public Message toEntity() {
        Message build = Message.builder()
                .id(id)
                .sender(sender)
                .receiver(receiver)
                .body(body)
                .chatRoom(chatRoom)
                .build();
        return build;
    }

    /**
     *  Entity -> class
     */

    @Builder
    public MessageDto(long id,String sender,String receiver, String body,long chatRoom,int isRead,LocalDateTime modifiedDate) {
        this.id=id;
        this.sender=sender;
        this.receiver=receiver;
        this.body=body;
        this.chatRoom=chatRoom;
        this.isRead=isRead;
        this.modifiedDate = modifiedDate;
    }
}