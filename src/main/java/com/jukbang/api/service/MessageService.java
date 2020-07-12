package com.jukbang.api.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jukbang.api.dto.MessageChatRoomDto;
import com.jukbang.api.dto.MessageDto;
import com.jukbang.api.model.Message;
import com.jukbang.api.repository.MessageChatRoomRepository;
import com.jukbang.api.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * import org.springframework.web.bind.annotation.PathVariable;
 * import org.hibernate.criterion.Example;
 * import java.util.Optional;
 **/


@AllArgsConstructor
@Service

public class MessageService {
    private MessageRepository messageRepository;
    private MessageChatRoomRepository messageChatRoomRepository;

    /**
     * Message list 에 표시되야할 정보 : sender, body, date, chatRoom
     */
    @Transactional
    public List<MessageDto> getMessageList(String userid, long chatroom) {
        List<Message> boardEntities = messageRepository.findAllByChatRoom(chatroom);
        List<MessageDto> boardDtoList = new ArrayList<>();

        for (Message boardEntity : boardEntities) {
            if (userid.equals(boardEntity.getReceiver()))
                boardEntity.setIsRead(0);
            MessageDto boardDTO = MessageDto.builder()
                    .id(boardEntity.getId())
                    .sender(boardEntity.getSender())
                    .receiver(boardEntity.getReceiver())
                    .modifiedDate(boardEntity.getModifiedDate())
                    .body(boardEntity.getBody())
                    .isRead(boardEntity.getIsRead())
                    .chatRoom(boardEntity.getChatRoom())
                    .build();

            boardDtoList.add(boardDTO);
        }


        return boardDtoList;
    }

    /**
     * 채팅방 만들기
     * 입력되야할 데이터 : user1, user2
     */
    @Transactional
    public long MakeChatRoom(String userId, String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MessageChatRoomDto messageChatRoomDto;
        messageChatRoomDto = objectMapper.readValue(json, MessageChatRoomDto.class);
        messageChatRoomDto.setBuyer(userId);
        return messageChatRoomRepository.save(messageChatRoomDto.toEntity()).getChatRoom();
    }


    /**
     * 채팅 보내기
     * 입력해야될 데이터 : body(내용), receiver(수신자)
     */
    @Transactional
    public long SendMessage(String userId, long chatRoom, String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MessageDto messageDto;
        messageDto = objectMapper.readValue(json, MessageDto.class);
        messageDto.setSender(userId);
        messageDto.setChatRoom(chatRoom);
        return messageRepository.save(messageDto.toEntity()).getId();
    }


    /**
     * 삭제하기
     * 댓글의 고유번호 (id) 에 접근하여 삭제
     */
    @Transactional
    public void DeleteMessage(long id) {
        messageRepository.deleteById(id);
    }
}

