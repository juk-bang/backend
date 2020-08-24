package com.jukbang.api.model;

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

public class Message extends Time {
    @Id
    /**
     *  각 댓글의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    /**
     * 쪽지를 보낸이 의 이름
     */
    @Column(length = 30, nullable = false)
    private String sender;

    /**
     * 쪽지를 받는이 의 이름
     */
    @Column(length = 30, nullable = false)
    private String receiver;


    /**
     * 100자 이내의 쪽지 내용 입력
     */
    @Column(length = 100, nullable = false)
    private String body;

    /**
     * 채팅방 번호
     */
    @Column(nullable = false)
    private long chatRoom;

    /**
     * 메시지 확인 여부
     */
    @Column(nullable = false)
    private int isRead = 1;


    @Builder
    public Message(long id, String sender, String body, String receiver, long chatRoom) {
        this.id = id;
        this.sender = sender;
        this.body = body;
        this.receiver = receiver;
        this.chatRoom = chatRoom;
        this.isRead = 1;

    }
}
