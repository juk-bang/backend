package com.jukbang.api.chat.service;

import com.jukbang.api.chat.dto.MessageDto;
import com.jukbang.api.chat.entity.Message;
import com.jukbang.api.chat.entity.MessageChatRoom;
import com.jukbang.api.chat.repository.MessageChatRoomRepository;
import com.jukbang.api.chat.repository.MessageRepository;
import com.jukbang.api.chat.request.CreateChatRoomRequest;
import com.jukbang.api.chat.request.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageChatRoomRepository messageChatRoomRepository;

    /**
     * Message list 에 표시되야할 정보 : sender, body, date, chatRoom
     */
    @Transactional
    public List<MessageDto> getMessageList(String userId, long chatroom) {
        List<Message> boardEntities = messageRepository.findAllByChatRoom(chatroom);
        List<MessageDto> boardDtoList = new ArrayList<>();

        for (Message boardEntity : boardEntities) {
            if (userId.equals(boardEntity.getReceiver()))
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
    public long MakeChatRoom(String userId, CreateChatRoomRequest createChatRoomRequest) {
        return messageChatRoomRepository.save(
                new MessageChatRoom(
                        createChatRoomRequest.getChatRoom(),
                        userId,
                        createChatRoomRequest.getSeller()
                )).getChatRoom();
    }


    /**
     * 채팅 보내기
     * 입력해야될 데이터 : body(내용), receiver(수신자)
     */
    @Transactional
    public long SendMessage(String userId, long chatRoom, SendMessageRequest sendMessageRequest) {
        return messageRepository.save(
                new Message(
                        sendMessageRequest.getId(),
                        userId,
                        sendMessageRequest.getReceiver(),
                        sendMessageRequest.getBody(),
                        chatRoom
                )).getId();
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

